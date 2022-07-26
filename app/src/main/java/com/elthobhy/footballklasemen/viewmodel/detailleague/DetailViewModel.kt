package com.elthobhy.footballklasemen.viewmodel.detailleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.footballklasemen.data.StandingsRepository
import com.elthobhy.footballklasemen.data.local.entity.allleagues.DetailLeague

class DetailViewModel(private val repository: StandingsRepository): ViewModel() {
    fun getDetailLeaguesById(id: String): LiveData<DetailLeague> {
        return repository.getDetailLeagueById(id)
    }
}