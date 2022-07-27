package com.elthobhy.footballklasemen.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.elthobhy.footballklasemen.data.local.LocalData
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.detailleague.DetailLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.DataResponseLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
import com.elthobhy.footballklasemen.data.remote.RemoteData
import com.elthobhy.footballklasemen.data.remote.response.response.SeasonsItemTest
import com.elthobhy.footballklasemen.data.remote.response.response.allleague.DataItem
import com.elthobhy.footballklasemen.data.remote.response.response.seasonleague.DataResponse
import com.elthobhy.footballklasemen.data.remote.response.response.seasonleague.SeasonsItem
import com.elthobhy.footballklasemen.data.remote.response.response.seasonleague.TypesItem
import com.elthobhy.footballklasemen.data.remote.response.vo.ApiResponse
import com.elthobhy.footballklasemen.utils.AppExecutors
import com.elthobhy.footballklasemen.utils.vo.Resource

class StandingsRepository private constructor(
    private val remoteData: RemoteData,
    private val appExecutors: AppExecutors,
    private val localData: LocalData
) : StandingsDataSource {
    companion object {
        @Volatile
        private var instance: StandingsRepository? = null
        fun getInstance(
            remoteData: RemoteData,
            appExecutors: AppExecutors,
            localData: LocalData
        ): StandingsRepository =
            instance ?: synchronized(this) {
                instance ?: StandingsRepository(
                    remoteData,
                    appExecutors,
                    localData
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
                for(response in data){
                    val season = SeasonLeague(
                        id = id,
                        year = response.year,
                        startDate = response.startDate,
                        endDate = response.endDate,
                        displayName = response.displayName
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
}