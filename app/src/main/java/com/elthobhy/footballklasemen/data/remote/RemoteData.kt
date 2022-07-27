package com.elthobhy.footballklasemen.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elthobhy.footballklasemen.data.remote.network.ApiConfig
import com.elthobhy.footballklasemen.data.remote.response.response.SeasonLeagueResponseTest
import com.elthobhy.footballklasemen.data.remote.response.response.SeasonsItemTest
import com.elthobhy.footballklasemen.data.remote.response.response.allleague.DataItem
import com.elthobhy.footballklasemen.data.remote.response.response.allleague.ResponseAllLeague
import com.elthobhy.footballklasemen.data.remote.response.vo.ApiResponse
import com.elthobhy.footballklasemen.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteData private constructor(){
    companion object{
        private var instance: RemoteData? = null
        fun getInstance(): RemoteData{
            return instance ?: synchronized(this){
                instance ?: RemoteData().apply {
                    instance = this
                }
            }
        }
    }

    fun getAllLeague(): LiveData<ApiResponse<List<DataItem>>>{
        EspressoIdlingResource.increment()
        val allLeague = MutableLiveData<ApiResponse<List<DataItem>>>()
        ApiConfig.getApiService().getAllLeague("leagues")
            .enqueue(object : Callback<ResponseAllLeague<DataItem>> {
                override fun onResponse(
                    call: Call<ResponseAllLeague<DataItem>>,
                    response: Response<ResponseAllLeague<DataItem>>
                ) {
                    if(response.isSuccessful){
                        allLeague.postValue(ApiResponse.success(response.body()?.data as List<DataItem>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ResponseAllLeague<DataItem>>, t: Throwable) {
                    allLeague.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }
            })
        return allLeague
    }

    fun getSeasonLeague(id: String): LiveData<ApiResponse<List<SeasonsItemTest>>>{
        EspressoIdlingResource.increment()
        val seasonLeague = MutableLiveData<ApiResponse<List<SeasonsItemTest>>>()
        ApiConfig.getApiService().getSeasonById(id)
            .enqueue(object : Callback<SeasonLeagueResponseTest>{
                override fun onResponse(
                    call: Call<SeasonLeagueResponseTest>,
                    response: Response<SeasonLeagueResponseTest>
                ) {
                    if(response.isSuccessful){
                        seasonLeague.postValue(ApiResponse.success(response.body()?.data?.seasons as List<SeasonsItemTest>))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(
                    call: Call<SeasonLeagueResponseTest>,
                    t: Throwable
                ) {
                    seasonLeague.postValue(
                        ApiResponse.error(
                            msg = t.message.toString(),
                            mutableListOf()
                        )
                    )
                    EspressoIdlingResource.decrement()
                }
            })
        return seasonLeague
    }
}