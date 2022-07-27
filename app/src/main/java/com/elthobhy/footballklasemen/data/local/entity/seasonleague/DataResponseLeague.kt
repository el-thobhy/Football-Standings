package com.elthobhy.footballklasemen.data.local.entity.seasonleague

import androidx.annotation.NonNull
import androidx.room.*
import com.elthobhy.footballklasemen.data.local.room.Converters
import com.elthobhy.footballklasemen.data.remote.response.response.seasonleague.SeasonsItem


@Entity(tableName = "data_response")
data class DataResponseLeague(

    @ColumnInfo(name = "season_response")
    @TypeConverters(Converters::class)
    val seasons: List<SeasonLeague>,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="name")
    val name: String,

    @ColumnInfo(name="abbreviation")
    val abbreviation: String? = null,

    @ColumnInfo(name="desc")
    val desc: String? = null
)