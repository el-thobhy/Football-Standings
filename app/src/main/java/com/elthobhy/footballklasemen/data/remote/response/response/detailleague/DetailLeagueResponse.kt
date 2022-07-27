package com.elthobhy.footballklasemen.data.remote.response.response.detailleague

import android.os.Parcelable
import com.elthobhy.footballklasemen.data.remote.response.response.allleague.Logos
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class DetailLeagueResponse<T>(

	@field:SerializedName("data")
	val dataItemDetail: List<T>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

@Parcelize
data class DataItemDetail(

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("id")
	val id: String? = null,

    @field:SerializedName("abbr")
	val abbr: String? = null,

    @field:SerializedName("logos")
	val logos: Logos? = null,

    @field:SerializedName("slug")
	val slug: String? = null
) : Parcelable


