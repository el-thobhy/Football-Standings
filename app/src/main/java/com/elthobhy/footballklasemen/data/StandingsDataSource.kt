package com.elthobhy.footballklasemen.data

import androidx.lifecycle.LiveData
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.detailleague.DetailLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.DataResponseLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
import com.elthobhy.footballklasemen.data.remote.response.response.SeasonsItemTest
import com.elthobhy.footballklasemen.data.remote.response.response.seasonleague.DataResponse
import com.elthobhy.footballklasemen.utils.vo.Resource

interface StandingsDataSource {
    fun getAllLeagues(): LiveData<Resource<List<AllLeagues>>>

    fun getDetailLeagueById(id:String): LiveData<DetailLeague>

    fun getSeasonById(id: String): LiveData<Resource<List<SeasonLeague>>>
}