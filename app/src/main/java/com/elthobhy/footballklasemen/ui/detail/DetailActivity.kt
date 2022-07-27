package com.elthobhy.footballklasemen.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.elthobhy.footballklasemen.R
import com.elthobhy.footballklasemen.data.local.entity.detailleague.DetailLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.DataResponseLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
import com.elthobhy.footballklasemen.data.remote.response.response.seasonleague.DataResponse
import com.elthobhy.footballklasemen.databinding.ActivityDetailBinding
import com.elthobhy.footballklasemen.utils.Constants
import com.elthobhy.footballklasemen.utils.vo.Status
import com.elthobhy.footballklasemen.viewmodel.detailleague.DetailViewModel
import com.elthobhy.footballklasemen.viewmodel.seasonleague.SeasonViewModel
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private val detailViewModel by inject<DetailViewModel>()
    private val seasonViewModel by inject<SeasonViewModel>()
    private val seasonAdapter by inject<SeasonAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rvSeason.apply {
                setHasFixedSize(true)
                adapter = seasonAdapter
            }
        }
        val id = intent?.getStringExtra(Constants.LEAGUE_ID)
        if(id!=null){
            detailViewModel.getDetailLeaguesById(id).observe(this){detail->
                displayDetail(detail)
                Log.e("detail", "onCreate: $detail $id", )
            }
        }
        if (id != null) {
            seasonViewModel.getSeasonLeagues(id).observe(this){season->
                if(season != null){
                    when(season.status){
                        Status.LOADING->{
                            Toast.makeText(this,"Loading",Toast.LENGTH_SHORT).show()
                        }
                        Status.SUCCESS->{
                            season.data?.let {
                                seasonAdapter.setList(it)
                                Log.e("Season", "onCreate: $it")
                            }
                        }
                        Status.ERROR->{
                            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                            Log.e("statusDetailError", "onCreate: $season", )
                        }
                    }
                }
            }
        }
        seasonAdapter.setOnItemClickCallback(object : SeasonAdapter.OnItemClickCallback{
            override fun onItemClicked(data: SeasonLeague) {
                Toast.makeText(this@DetailActivity,"Clicked",Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun displayDetail(detail: DetailLeague?) {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(detail?.logos?.light)
                .placeholder(R.drawable.ic_baseline_broken_image_24)
                .into(imgLeagueDetail)
            leagueNameDetail.text = detail?.name
        }
    }


}