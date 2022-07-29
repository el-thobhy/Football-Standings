package com.elthobhy.footballklasemen.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.elthobhy.footballklasemen.data.local.LocalData
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.detailleague.DetailLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
import com.elthobhy.footballklasemen.data.local.room.StandingsDao
import com.elthobhy.footballklasemen.data.remote.RemoteData
import com.elthobhy.footballklasemen.data.remote.network.ApiService
import com.elthobhy.footballklasemen.data.remote.response.response.seasonleague.SeasonsItemTest
import com.elthobhy.footballklasemen.data.remote.response.response.allleague.DataItem
import com.elthobhy.footballklasemen.data.remote.response.response.standings.StandingsLeagueResponse
import com.elthobhy.footballklasemen.data.remote.response.vo.ApiResponse
import com.elthobhy.footballklasemen.utils.AppExecutors
import com.elthobhy.footballklasemen.utils.vo.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StandingsRepository private constructor(
    private val remoteData: RemoteData,
    private val appExecutors: AppExecutors,
    private val localData: LocalData,
    private val apiService: ApiService,
    private val dao: StandingsDao
) : StandingsDataSource {
    companion object {
        @Volatile
        private var instance: StandingsRepository? = null
        fun getInstance(
            remoteData: RemoteData,
            appExecutors: AppExecutors,
            localData: LocalData,
            apiService: ApiService,
            dao: StandingsDao
        ): StandingsRepository =
            instance ?: synchronized(this) {
                instance ?: StandingsRepository(
                    remoteData,
                    appExecutors,
                    localData,
                    apiService,
                    dao
                ).apply {
                    instance = this
                }
            }
    }

    override fun getAllLeagues(): LiveData<Resource<List<AllLeagues>>> {
        return object : NetworkBoundResource<List<AllLeagues>, List<DataItem>>(appExecutors) {
            override fun shouldFetch(data: List<AllLeagues>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: List<DataItem>) {
                val listAllLeagues = ArrayList<AllLeagues>()
                for (response in data) {
                    val leagues = response.id?.let {
                        AllLeagues(
                            id = it,
                            abbr = response.abbr,
                            name = response.name,
                            logos = response.logos
                        )
                    }
                    if (leagues != null) {
                        listAllLeagues.add(leagues)
                    }
                }
                localData.insertAllLeagues(listAllLeagues)
            }

            override fun createCall(): LiveData<ApiResponse<List<DataItem>>> {
                return remoteData.getAllLeague()
            }

            override fun loadFromDB(): LiveData<List<AllLeagues>> {
                return localData.getAllLeagues()
            }
        }.asLiveData()
    }

    override fun getDetailLeagueById(id: String): LiveData<DetailLeague> {
        return localData.getDetailLeague(id)
    }


    override fun getSeasonById(id: String): LiveData<Resource<List<SeasonLeague>>> {
        return object :
            NetworkBoundResource<List<SeasonLeague>, List<SeasonsItemTest>>(
                appExecutors
            ) {
            override fun shouldFetch(data: List<SeasonLeague>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: List<SeasonsItemTest>) {
                val listSeason = ArrayList<SeasonLeague>()
                for (response in data) {
                    val season = SeasonLeague(
                        id = id,
                        year = response.year,
                        startDate = response.startDate,
                        endDate = response.endDate,
                        displayName = response.displayName,
                    )
                    listSeason.add(season)
                }
                localData.insertSeason(listSeason)
            }

            override fun createCall(): LiveData<ApiResponse<List<SeasonsItemTest>>> {
                return remoteData.getSeasonLeague(id)
            }

            override fun loadFromDB(): LiveData<List<SeasonLeague>> {
                return localData.getSeasonLeague(id)
            }
        }.asLiveData()
    }

    override fun fetchStanding(id: String,season: Int,onSuccess: (StandingsLeagueResponse) -> Unit, onError: (String)-> Unit) {
        val response = apiService.getDetailStandingByYear(id, season)
        response.enqueue(object : Callback<StandingsLeagueResponse>{
            override fun onResponse(
                call: Call<StandingsLeagueResponse>,
                response: Response<StandingsLeagueResponse>
            ) {
                if(response.isSuccessful){
                    Thread{
                        dao.insertStandingLeague(response.body()!!)
                        onSuccess(response.body()!!)
                        Log.e("standingRepo", "onResponse: ${response.body()}", )
                    }.start()
                }else{
                    onError(response.message())
                }
            }

            override fun onFailure(call: Call<StandingsLeagueResponse>, t: Throwable) {
                onError(t.localizedMessage ?: "Something went wrong")
            }

        })
    }

    override fun getStandingLocal(onSuccess: (StandingsLeagueResponse?) -> Unit) {
        Thread{
            onSuccess(dao.getStandingLeagues())
        }
    }
}