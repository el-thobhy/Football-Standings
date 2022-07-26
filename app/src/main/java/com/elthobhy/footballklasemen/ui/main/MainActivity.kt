package com.elthobhy.footballklasemen.ui.main

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.elthobhy.footballklasemen.data.local.entity.allleagues.AllLeagues
import com.elthobhy.footballklasemen.databinding.ActivityMainBinding
import com.elthobhy.footballklasemen.ui.detail.DetailActivity
import com.elthobhy.footballklasemen.utils.Constants
import com.elthobhy.footballklasemen.utils.vo.Status
import com.elthobhy.footballklasemen.viewmodel.allleagues.AllLeaguesViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val allLeaguesAdapter by inject<AllLeaguesAdapter>()
    private val allLeaguesViewModel by inject<AllLeaguesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        showRvAllLeague()
        setContentView(binding.root)
    }

    private fun showRvAllLeague() {
        allLeaguesAdapter.notifyDataSetChanged()
        binding.apply {
            rvAllLeagues.apply {
                setHasFixedSize(true)
                adapter = allLeaguesAdapter
            }
            allLeaguesViewModel.getAllLeagues().observe(this@MainActivity){listAllLeagues->
                if(listAllLeagues != null){
                    when(listAllLeagues.status){
                        Status.LOADING->{
                            Toast.makeText(this@MainActivity,"Loading",Toast.LENGTH_LONG).show()
                        }
                        Status.SUCCESS->{
                            listAllLeagues.data?.let { allLeaguesAdapter.setList(it) }
                        }
                        Status.ERROR->{
                            Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        allLeaguesAdapter.setOnItemClickCallback(object : AllLeaguesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: AllLeagues) {
                showDetail(data)
            }

        })

    }
    private fun showDetail(data: AllLeagues) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(Constants.LEAGUE_ID, data.id)
        startActivity(intent)
    }
}