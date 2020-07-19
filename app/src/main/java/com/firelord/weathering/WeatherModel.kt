package com.firelord.weathering

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel(
    val tvTemp: String,
    val tvRain: String,
    val tvHumidity: String,
    val tvWind: String,
    val tvDay: String?,
    val tvDate: String?
) : Parcelable