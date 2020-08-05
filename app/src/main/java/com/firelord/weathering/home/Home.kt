package com.firelord.weathering.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firelord.weathering.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var homeActivity: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeActivity = ActivityHomeBinding.inflate(layoutInflater)
        val view = homeActivity.root
        setContentView(view)

        val weatherInfo = intent.extras!!.getParcelable<WeatherModel>("weatherModel")
        homeActivity.tvTemp.text = weatherInfo!!.tvTemp
        homeActivity.tvRain.text = weatherInfo.tvRain
        homeActivity.tvHumidity.text = weatherInfo.tvHumidity
        homeActivity.tvWind.text = weatherInfo.tvWindSpeed
        homeActivity.tvWeatherType.text = weatherInfo.tvWeatherType
        homeActivity.tvDate.text = weatherInfo.tvDate
        homeActivity.tvLocation.text = weatherInfo.tvLocation
        homeActivity.clCard.setBackgroundResource(weatherInfo.clCard)
        homeActivity.tvRainName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, weatherInfo.tvRainName, 0, 0
        )
        homeActivity.tvHumidityName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, weatherInfo.tvHumidityName, 0, 0
        )
        homeActivity.tvWindName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, weatherInfo.tvWindName, 0, 0
        )
        homeActivity.tvWeatherType.setTextColor(getColor(weatherInfo.tvWeatherTypeColor))

    }
}