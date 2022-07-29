package com.elthobhy.footballklasemen.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.detailleague.DetailLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
import com.elthobhy.footballklasemen.data.remote.response.response.standings.StandingsLeagueResponse

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



    @Update
    fun updateSeasonLeagues(seasonLeagues: SeasonLeague)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeasonLeagues(seasonLeagues: List<SeasonLeague>)

    @Query("SELECT * FROM season_league WHERE id = :id")
    fun getSeasonLeagues(id: String): LiveData<List<SeasonLeague>>

    @Update
    fun updateStandingLeagues(seasonLeagues: StandingsLeagueResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStandingLeague(seasonLeagues: StandingsLeagueResponse)

    @Query("SELECT * FROM tbl_standing_data")
    fun getStandingLeagues(): StandingsLeagueResponse
}