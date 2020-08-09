package com.firelord.weathering.intro.data.response


import com.google.gson.annotations.SerializedName

data class Wind(
    val deg: Int,
    val speed: Double
)