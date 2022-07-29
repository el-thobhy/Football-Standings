package com.elthobhy.footballklasemen.data.remote.network

import com.elthobhy.footballklasemen.data.remote.response.response.seasonleague.SeasonLeagueResponse
import com.elthobhy.footballklasemen.data.remote.response.response.allleague.DataItem
import com.elthobhy.footballklasemen.data.remote.response.response.allleague.ResponseAllLeague
import com.elthobhy.footballklasemen.data.remote.response.response.standings.StandingsLeagueResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/{leagues}")
    fun getAllLeague(
        @Path("leagues") leagues: String
    ): Call<ResponseAllLeague<DataItem>>

    @GET("/leagues/{id}/seasons")
    fun getSeasonById(
        @Path("id") id: String
    ): Call<SeasonLeagueResponse>

    @GET("/leagues/{id}/standings")
    fun getDetailStandingByYear(
        @Path("id") id: String,
        @Query("season") season: Int
    ): Call<StandingsLeagueResponse>
}