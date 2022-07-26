package com.elthobhy.footballklasemen.data.local

import androidx.lifecycle.LiveData
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.allleagues.DetailLeague
import com.elthobhy.footballklasemen.data.local.room.StandingsDao

class LocalData private constructor(private val standingsDao: StandingsDao){
    companion object{
        private var INSTANCE: LocalData? = null
        fun getInstance(standingsDao: StandingsDao): LocalData =
            INSTANCE ?: LocalData(standingsDao).apply {
                INSTANCE = this
            }
    }
    fun getAllLeagues(): LiveData<List<AllLeagues>> = standingsDao.getAllLeagues()
    fun insertAllLeagues(allLeagues: List<AllLeagues>) = standingsDao.insertAllLeagues(allLeagues)

    fun getDetailLeague(id:String): LiveData<DetailLeague> = standingsDao.getDetailLeagues(id)
}