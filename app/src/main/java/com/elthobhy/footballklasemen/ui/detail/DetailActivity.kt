package com.elthobhy.footballklasemen.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.elthobhy.footballklasemen.R
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.data.local.entity.allleagues.DetailLeague
import com.elthobhy.footballklasemen.databinding.ActivityDetailBinding
import com.elthobhy.footballklasemen.utils.Constants
import com.elthobhy.footballklasemen.viewmodel.detailleague.DetailViewModel
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private val detailViewModel by inject<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent?.getStringExtra(Constants.LEAGUE_ID)
        if(id!=null){
            detailViewModel.getDetailLeaguesById(id).observe(this){detail->
                displayDetail(detail)
                Log.e("detail", "onCreate: $detail $id", )
            }
        }

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