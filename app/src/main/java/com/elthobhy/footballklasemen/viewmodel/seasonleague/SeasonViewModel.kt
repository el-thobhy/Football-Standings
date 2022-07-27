package com.elthobhy.footballklasemen.viewmodel.seasonleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.footballklasemen.data.StandingsRepository
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.DataResponseLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
import com.elthobhy.footballklasemen.data.remote.response.response.seasonleague.DataResponse
import com.elthobhy.footballklasemen.utils.vo.Resource

class SeasonViewModel(private val repository: StandingsRepository): ViewModel() {
    fun getSeasonLeagues(id: String): LiveData<Resource<List<SeasonLeague>>> {
        return repository.getSeasonById(id)
    }
}