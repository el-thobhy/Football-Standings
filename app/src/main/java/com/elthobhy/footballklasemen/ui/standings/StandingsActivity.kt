package com.elthobhy.footballklasemen.ui.standings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.elthobhy.footballklasemen.R
import com.elthobhy.footballklasemen.data.local.entity.detailleague.DetailLeague
import com.elthobhy.footballklasemen.databinding.ActivityStandingsBinding
import com.elthobhy.footballklasemen.utils.Constants
import com.elthobhy.footballklasemen.utils.Constants.STANDING_NAME
import com.elthobhy.footballklasemen.viewmodel.detailleague.DetailViewModel
import com.elthobhy.footballklasemen.viewmodel.standingleague.StandingViewModel
import org.koin.android.ext.android.inject

class StandingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStandingsBinding
    private val standingViewModel by inject<StandingViewModel>()
    private val adapterStanding by inject<StandingAdapter>()
    private val detailViewModel by inject<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStandingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent?.getStringExtra(Constants.LEAGUE_ID)
        val year = intent?.getIntExtra(Constants.STANDING_YEAR,0)
        showDetail(id)
        binding.rvStandings.visibility = View.GONE
        if (id != null&& year != null) {
            binding.rvStandings.visibility = View.VISIBLE
            standingViewModel.onCreate(id,year)
        }
        showRv()
    }

    private fun showRv() {
        binding.progressCircular.visibility=View.VISIBLE
        binding.apply {
            rvStandings.apply {
                layoutManager = LinearLayoutManager(this@StandingsActivity,LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
                adapter = adapterStanding
            }
        }
        standingViewModel.standingResponse.observe(this){
            binding.progressCircular.visibility=View.GONE
            adapterStanding.setList(it?.dataStandingsItem?.standings)
        }
        standingViewModel.errorResponse.observe(this){
            Log.e("gagal", "showRv: $it" )
        }
    }


    private fun showDetail(id: String?) {
        if (id != null) {
            detailViewModel.getDetailLeaguesById(id).observe(this) { detail ->
                displayDetail(detail)
            }
        }
    }

    private fun displayDetail(detail: DetailLeague) {
        val name = intent.getStringExtra(STANDING_NAME)
        binding.apply {
            Glide.with(this@StandingsActivity)
                .load(detail.logos?.dark)
                .placeholder(R.drawable.ic_baseline_broken_image_24)
                .into(imgLeagueDetail)
            leagueNameDetail.text = name
        }
    }
}