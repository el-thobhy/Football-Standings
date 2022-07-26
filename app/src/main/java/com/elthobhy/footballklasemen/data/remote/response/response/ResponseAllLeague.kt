package com.elthobhy.footballklasemen.data.remote.response.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ResponseAllLeague<T>(

	@field:SerializedName("data")
	val data: List<T>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

@Parcelize
data class Logos(

	@field:SerializedName("light")
	val light: String? = null,

	@field:SerializedName("dark")
	val dark: String? = null
) : Parcelable

@Parcelize
data class DataItem(

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
