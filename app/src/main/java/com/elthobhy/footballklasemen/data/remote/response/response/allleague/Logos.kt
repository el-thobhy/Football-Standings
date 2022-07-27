package com.elthobhy.footballklasemen.data.remote.response.response.allleague

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Logos(

    @field:SerializedName("light")
    val light: String? = null,

    @field:SerializedName("dark")
    val dark: String? = null
) : Parcelable