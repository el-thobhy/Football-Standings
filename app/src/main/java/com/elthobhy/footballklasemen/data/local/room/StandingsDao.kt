package com.elthobhy.footballklasemen.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.allleagues.DetailLeague

@Dao
interface StandingsDao {
    @Update
    fun updateAllLeagues(allLeagues: AllLeagues)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLeagues(allLeagues: List<AllLeagues>)

    @Query("SELECT * FROM all_league")
    fun getAllLeagues(): LiveData<List<AllLeagues>>

    @Query("SELECT * FROM all_league WHERE bookmarked = 1")
    fun getBookmarkedLeagues(): LiveData<List<AllLeagues>>

    @Query("SELECT * FROM all_league WHERE id= :id")
    fun getDetailLeagues(id: String): LiveData<DetailLeague>

}