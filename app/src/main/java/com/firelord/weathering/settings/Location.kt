package com.firelord.weathering.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firelord.weathering.databinding.ActivityLocationBinding

class Location : AppCompatActivity() {

    private lateinit var locationActivity:ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationActivity = ActivityLocationBinding.inflate(layoutInflater)
        val view = locationActivity.root
        setContentView(view)
    }
}