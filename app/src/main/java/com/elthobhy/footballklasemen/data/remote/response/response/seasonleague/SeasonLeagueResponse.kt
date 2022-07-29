package com.elthobhy.footballklasemen.data.remote.response.response.seasonleague

import com.google.gson.annotations.SerializedName

data class SeasonLeagueResponse(

	@field:SerializedName("data")
	val data: DataTest,

	@field:SerializedName("status")
	val status: Boolean
)

data class SeasonsItemTest(

	@field:SerializedName("types")
	val types: List<TypesItemTest>,

	@field:SerializedName("year")
	val year: Int,

	@field:SerializedName("endDate")
	val endDate: String,

	@field:SerializedName("displayName")
	val displayName: String,

	@field:SerializedName("startDate")
	val startDate: String
)

data class TypesItemTest(

	@field:SerializedName("hasStandings")
	val hasStandings: Boolean,

	@field:SerializedName("endDate")
	val endDate: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("abbreviation")
	val abbreviation: String,

	@field:SerializedName("startDate")
	val startDate: String
)

data class DataTest(

	@field:SerializedName("seasons")
	val seasons: List<SeasonsItemTest>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("abbreviation")
	val abbreviation: String,

	@field:SerializedName("desc")
	val desc: String
)
