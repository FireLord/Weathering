package com.firelord.weathering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firelord.weathering.databinding.ActivitySnowBinding

class Snow : AppCompatActivity() {

    private lateinit var snowActivity: ActivitySnowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        snowActivity = ActivitySnowBinding.inflate(layoutInflater)
        val view = snowActivity.root
        setContentView(view)

        supportActionBar?.elevation = 0F

        val weatherInfo = intent.extras!!.getParcelable<WeatherModel>("weatherModel")
        snowActivity.tvTemp.text = weatherInfo!!.tvTemp
        snowActivity.tvRain.text = weatherInfo.tvRain
        snowActivity.tvHumidity.text = weatherInfo.tvHumidity
        snowActivity.tvWind.text = weatherInfo.tvWind
        snowActivity.tvDay.text = weatherInfo.tvDay
        snowActivity.tvDate.text = weatherInfo.tvDate
        snowActivity.tvLocation.text = weatherInfo.tvLocation
    }
}