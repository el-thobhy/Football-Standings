package com.elthobhy.footballklasemen.data.local.room

import androidx.room.TypeConverter
import com.elthobhy.footballklasemen.data.remote.response.response.standings.StatsItem
import com.google.gson.Gson

class ConvertersStats {
    @TypeConverter
    fun listToJson(value: List<StatsItem>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<StatsItem>::class.java).toList()

}