package com.elthobhy.footballklasemen.data

import androidx.lifecycle.LiveData
import com.elthobhy.footballklasemen.data.local.LocalData
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.allleagues.DetailLeague
import com.elthobhy.footballklasemen.data.remote.RemoteData
import com.elthobhy.footballklasemen.data.remote.response.response.DataItem
import com.elthobhy.footballklasemen.data.remote.response.response.DataItemDetail
import com.elthobhy.footballklasemen.data.remote.response.vo.ApiResponse
import com.elthobhy.footballklasemen.utils.AppExecutors
import com.elthobhy.footballklasemen.utils.vo.Resource

class StandingsRepository private constructor(
    private val remoteData: RemoteData,
    private val appExecutors : AppExecutors,
    private val localData: LocalData
): StandingsDataSource {
    companion object{
        @Volatile
        private var instance: StandingsRepository? =null
        fun getInstance(
            remoteData: RemoteData,
            appExecutors: AppExecutors,
            localData: LocalData
        ): StandingsRepository =
            instance ?: synchronized(this){
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
        return object : NetworkBoundResource<List<AllLeagues>,List<DataItem>>(appExecutors){
            override fun shouldFetch(data: List<AllLeagues>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: List<DataItem>) {
                val listAllLeagues = ArrayList<AllLeagues>()
                for(response in data){
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
}