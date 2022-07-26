package com.elthobhy.footballklasemen.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.allleagues.DetailLeague

@Database(entities = [AllLeagues::class, DetailLeague::class], version = 1, exportSchema = false)
abstract class StandingsDatabase: RoomDatabase() {
    abstract fun standingsDao(): StandingsDao

    companion object{
        private var INSTANCE: StandingsDatabase? = null

        fun getInstance(context: Context): StandingsDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    StandingsDatabase::class.java,
                    "standings-db"
                ).build().apply { INSTANCE = this }
            }
    }
}