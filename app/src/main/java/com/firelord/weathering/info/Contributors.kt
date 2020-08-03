package com.firelord.weathering.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firelord.weathering.R
import com.firelord.weathering.databinding.ActivityContributorsBinding

class Contributors : AppCompatActivity() {

    private lateinit var contributorsActivity: ActivityContributorsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contributorsActivity = ActivityContributorsBinding.inflate(layoutInflater)
        val view = contributorsActivity.root
        setContentView(view)
    }
}