package com.elthobhy.footballklasemen.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.detailleague.DetailLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
import com.elthobhy.footballklasemen.data.remote.response.response.standings.StandingsLeagueResponse

@Database(
    entities = [
        AllLeagues::class,
        DetailLeague::class,
        StandingsLeagueResponse::class,
        SeasonLeague::class,], version = 1, exportSchema = false
)
@TypeConverters(ConvertersStats::class,ConverterStanding::class)
abstract class StandingsDatabase : RoomDatabase() {
    abstract fun standingsDao(): StandingsDao

    companion object {
        private var INSTANCE: StandingsDatabase? = null

        fun getInstance(context: Context): StandingsDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    StandingsDatabase::class.java,
                    "standings-db"
                ).build().apply { INSTANCE = this }
            }
    }
}