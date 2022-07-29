package com.elthobhy.footballklasemen.viewmodel.standingleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.footballklasemen.data.StandingsRepository
import com.elthobhy.footballklasemen.data.remote.response.response.standings.StandingsLeagueResponse
import com.elthobhy.footballklasemen.utils.Constants.SOMETHING_WENT_WRONG
import com.elthobhy.footballklasemen.utils.NetworkHelper

class StandingViewModel(
    private val repository: StandingsRepository,
    private val networkHelper: NetworkHelper
): ViewModel() {

    private val _response = MutableLiveData<StandingsLeagueResponse>()
    val standingResponse: LiveData<StandingsLeagueResponse> get() = _response

    private val _error = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _error

    fun onCreate(id: String, season: Int){
        if(networkHelper.isNetworkConnected()){
            repository.fetchStanding(id=id,season=season,{standingsLeagueResponse ->
                _response.postValue(standingsLeagueResponse)
            },{errorResponse->
                _error.postValue(errorResponse)
            })
        }else{
            repository.getStandingLocal { standingsLeagueResponse ->
                if(standingsLeagueResponse!=null&& standingsLeagueResponse.dataStandingsItem.standings?.isNotEmpty() == true){
                    _response.postValue(standingsLeagueResponse)
                }else{
                    _error.postValue(SOMETHING_WENT_WRONG)
                }
            }
        }
    }
}