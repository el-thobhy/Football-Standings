package com.elthobhy.footballklasemen.data.local.entity.allleagues

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.*
import com.elthobhy.footballklasemen.data.remote.response.response.Logos
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "all_league")
data class AllLeagues (
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
) : Parcelable