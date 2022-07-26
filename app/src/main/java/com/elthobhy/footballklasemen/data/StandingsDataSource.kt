package com.elthobhy.footballklasemen.data

import androidx.lifecycle.LiveData
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.utils.vo.Resource

interface StandingsDataSource {
    fun getAllLeagues(): LiveData<Resource<List<AllLeagues>>>
}