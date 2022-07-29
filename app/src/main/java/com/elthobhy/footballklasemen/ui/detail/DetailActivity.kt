package com.elthobhy.footballklasemen.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.elthobhy.footballklasemen.R
import com.elthobhy.footballklasemen.data.local.entity.detailleague.DetailLeague
import com.elthobhy.footballklasemen.data.local.entity.seasonleague.SeasonLeague
import com.elthobhy.footballklasemen.databinding.ActivityDetailBinding
import com.elthobhy.footballklasemen.ui.standings.StandingsActivity
import com.elthobhy.footballklasemen.utils.Constants
import com.elthobhy.footballklasemen.utils.vo.Status
import com.elthobhy.footballklasemen.viewmodel.detailleague.DetailViewModel
import com.elthobhy.footballklasemen.viewmodel.seasonleague.SeasonViewModel
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
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
        if (id != null) {
            detailViewModel.getDetailLeaguesById(id).observe(this) { detail ->
                displayDetail(detail)
            }
        }
        if (id != null) {
            seasonViewModel.getSeasonLeagues(id).observe(this) { season ->
                if (season != null) {
                    when (season.status) {
                        Status.LOADING -> {
                            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                        }
                        Status.SUCCESS -> {
                            season.data?.let {
                                seasonAdapter.setList(it)
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                            Log.e("statusDetailError", "onCreate: $season")
                        }
                    }
                }
            }
        }
        seasonAdapter.setOnItemClickCallback(object : SeasonAdapter.OnItemClickCallback {
            override fun onItemClicked(data: SeasonLeague) {
                showStanding(data)
            }

        })

    }

    private fun showStanding(data: SeasonLeague) {
        val intent = Intent(this, StandingsActivity::class.java)
        intent.putExtra(Constants.STANDING_YEAR, data.year)
            .putExtra(Constants.STANDING_NAME, data.displayName)
            .putExtra(Constants.LEAGUE_ID,data.id)
            .putExtra(Constants.SEASON, data.year)
        startActivity(intent)
    }

    private fun displayDetail(detail: DetailLeague?) {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(detail?.logos?.dark)
                .placeholder(R.drawable.ic_baseline_broken_image_24)
                .into(imgLeagueDetail)
            leagueNameDetail.text = detail?.name
        }
    }


}