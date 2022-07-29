package com.elthobhy.footballklasemen.data.local

import android.util.Log
import androidx.lifecycle.LiveData
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.detailleague.DetailLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
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

    fun getSeasonLeague(id: String): LiveData<List<SeasonLeague>> {
        Log.e("id", "getSeasonLeague: $id" )
      return standingsDao.getSeasonLeagues(id)
    }
    fun insertSeason(season: List<SeasonLeague>) = standingsDao.insertSeasonLeagues(season)
}