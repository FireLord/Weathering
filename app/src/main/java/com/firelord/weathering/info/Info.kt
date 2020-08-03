package com.firelord.weathering.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firelord.weathering.R
import com.firelord.weathering.databinding.ActivityInfoBinding

class Info : AppCompatActivity() {

    private lateinit var infoActivity:ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infoActivity = ActivityInfoBinding.inflate(layoutInflater)
        val view = infoActivity.root
        setContentView(view)
    }
}