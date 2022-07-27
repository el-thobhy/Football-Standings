package com.elthobhy.footballklasemen.data.local.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<SeasonLeague>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<SeasonLeague>::class.java).toList()
}