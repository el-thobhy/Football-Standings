package com.elthobhy.footballklasemen.viewmodel.allleagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elthobhy.footballklasemen.data.StandingsRepository
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.utils.vo.Resource

class AllLeaguesViewModel(private val repository: StandingsRepository): ViewModel() {
    fun getAllLeagues(): LiveData<Resource<List<AllLeagues>>>{
        return repository.getAllLeagues()
    }
}