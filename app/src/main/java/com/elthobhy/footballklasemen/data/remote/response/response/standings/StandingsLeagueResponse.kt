package com.elthobhy.footballklasemen.data.remote.response.response.standings

import android.os.Parcelable
import androidx.room.*
import com.elthobhy.footballklasemen.data.local.room.ConverterStanding
import com.elthobhy.footballklasemen.data.local.room.ConvertersStats
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tbl_standing_data")
data class StandingsLeagueResponse(

	@Embedded
	@field:SerializedName("data")
	val dataStandingsItem: DataStandingsItem,

	@PrimaryKey
	@field:SerializedName("status")
	val status: Boolean? = null
)


data class StatsItem(

	@field:SerializedName("shortDisplayName")
	val shortDisplayName: String? = null,

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("displayValue")
	val displayValue: String? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("abbreviation")
	val abbreviation: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

@Parcelize
data class Team(

	@field:SerializedName("shortDisplayName")
	val shortDisplayName: String? = null,

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id_team")
	val id_team: String? = null,

	@field:SerializedName("abbreviation")
	val abbreviation: String? = null,

	@field:SerializedName("isActive")
	val isActive: Boolean? = null,
) : Parcelable


data class DataStandingsItem(
	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("season")
	val season: Int? = null,

	@field:SerializedName("abbreviation")
	val abbreviation: String? = null,

	@field:SerializedName("seasonDisplay")
	val seasonDisplay: String? = null,

	@ColumnInfo(name="standing_item_")
	@TypeConverters(ConverterStanding::class)
	@field:SerializedName("standings")
	val standings: List<StandingsItem>? = null
)


data class StandingsItem(


	@ColumnInfo(name="stats_item_")
	@TypeConverters(ConvertersStats::class)
	@field:SerializedName("stats")
	val stats: List<StatsItem>? = null,

	@Embedded
	@field:SerializedName("team")
	val team: Team? = null
)

