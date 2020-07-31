package com.firelord.weathering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firelord.weathering.databinding.ActivitySunnyBinding

class Sunny : AppCompatActivity() {

    private lateinit var sunnyActivity: ActivitySunnyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sunnyActivity = ActivitySunnyBinding.inflate(layoutInflater)
        val view = sunnyActivity.root
        setContentView(view)

        val weatherInfo = intent.extras!!.getParcelable<WeatherModel>("weatherModel")
        sunnyActivity.tvTemp.text = weatherInfo!!.tvTemp
        sunnyActivity.tvRain.text = weatherInfo.tvRain
        sunnyActivity.tvHumidity.text = weatherInfo.tvHumidity
        sunnyActivity.tvWind.text = weatherInfo.tvWindSpeed
        sunnyActivity.tvWeatherType.text = weatherInfo.tvWeatherType
        sunnyActivity.tvDate.text = weatherInfo.tvDate
        sunnyActivity.tvLocation.text = weatherInfo.tvLocation

        // Bottom bar


    }
}