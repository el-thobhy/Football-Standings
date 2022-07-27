package com.elthobhy.footballklasemen.data.local.entity.seasonleague

import androidx.annotation.NonNull
import androidx.room.*
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.room.Converters

@Entity(tableName = "season_league")
data class SeasonLeague(
    @ColumnInfo(name = "id")
    val id : String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="year")
    val year: Int,

    @ColumnInfo(name="endDate")
    val endDate: String? = null,

    @ColumnInfo(name="displayName")
    val displayName: String? = null,

    @ColumnInfo(name="startDate")
    val startDate: String? = null
)