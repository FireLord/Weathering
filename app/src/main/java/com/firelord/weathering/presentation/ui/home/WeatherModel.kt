package com.firelord.weathering.presentation.ui.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel(
    val tvTemp: String,
    val tvRain: String,
    val tvHumidity: String,
    val tvWindSpeed: String,
    val tvWeatherType: String?,
    val tvDate: String?,
    val tvLocation: String,
    val ivBgWeather: Int,
    val tvRainName: Int,
    val tvHumidityName: Int,
    val tvWindName: Int,
    val tvWeatherTypeColor: Int
) : Parcelable