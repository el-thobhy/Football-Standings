package com.elthobhy.footballklasemen.data.remote.network

import com.elthobhy.footballklasemen.data.remote.response.response.SeasonLeagueResponseTest
import com.elthobhy.footballklasemen.data.remote.response.response.allleague.DataItem
import com.elthobhy.footballklasemen.data.remote.response.response.allleague.ResponseAllLeague
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/{leagues}")
    fun getAllLeague(
        @Path("leagues") leagues: String
    ): Call<ResponseAllLeague<DataItem>>

    @GET("/leagues/{id}/seasons")
    fun getSeasonById(
        @Path("id") id: String
    ): Call<SeasonLeagueResponseTest>
}