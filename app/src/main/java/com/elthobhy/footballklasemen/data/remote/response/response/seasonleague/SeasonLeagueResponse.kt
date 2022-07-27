package com.elthobhy.footballklasemen.data.remote.response.response.seasonleague

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class SeasonLeagueResponse<T>(

	@field:SerializedName("data")
	val dataResponse: DataResponse<T?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataResponse<T>(

	@field:SerializedName("seasons")
	val seasons: List<T?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("abbreviation")
	val abbreviation: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
)

@Parcelize
data class TypesItem(

	@field:SerializedName("hasStandings")
	val hasStandings: Boolean? = null,

	@field:SerializedName("endDate")
	val endDate: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("abbreviation")
	val abbreviation: String? = null,

	@field:SerializedName("startDate")
	val startDate: String? = null
) : Parcelable

data class SeasonsItem<T>(

	@field:SerializedName("types")
	val types: List<T>? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("endDate")
	val endDate: String? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("startDate")
	val startDate: String? = null
)
