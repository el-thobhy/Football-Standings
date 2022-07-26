package com.elthobhy.footballklasemen.data.remote.network

import com.elthobhy.footballklasemen.data.remote.response.response.DataItem
import com.elthobhy.footballklasemen.data.remote.response.response.ResponseAllLeague
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("https://api-football-standings.azharimm.site/{leagues}")
    fun getAllLeague(
        @Path("leagues") leagues: String
    ): Call<ResponseAllLeague<DataItem>>
}