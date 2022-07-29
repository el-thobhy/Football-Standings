package com.elthobhy.footballklasemen.data.local.room

import androidx.room.TypeConverter
import com.elthobhy.footballklasemen.data.remote.response.response.standings.StandingsItem
import com.google.gson.Gson

class ConverterStanding {
    @TypeConverter
    fun listToJson(value: List<StandingsItem>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<StandingsItem>::class.java).toList()

}