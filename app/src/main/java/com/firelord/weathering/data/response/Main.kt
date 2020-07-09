package com.firelord.weathering.data.response


import com.google.gson.annotations.SerializedName

data class Main(
    // Temp in kelvin
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    val humidity: Int,
    val pressure: Int
)