package com.elthobhy.footballklasemen.ui.standings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elthobhy.footballklasemen.databinding.ActivityStandingsBinding

class StandingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStandingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStandingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}