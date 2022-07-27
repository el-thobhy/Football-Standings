package com.elthobhy.footballklasemen.data.local.entity.detailleague

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elthobhy.footballklasemen.data.remote.response.response.allleague.Logos
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "detail_league")
data class DetailLeague(

    @ColumnInfo(name = "name")
    val name: String? = null,

    @PrimaryKey
    @Nullable
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name="abbr")
    val abbr: String? = null,

    @Embedded
    val logos: Logos? = null,

    @ColumnInfo(name = "slug")
    val slug: String? = null,

    @ColumnInfo(name= "bookmarked")
    var bookmarked: Boolean = false,
) : Parcelable{
    constructor(): this("","","",null,"",false)
}