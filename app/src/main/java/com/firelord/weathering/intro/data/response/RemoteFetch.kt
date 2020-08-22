package com.firelord.weathering.intro.data.response

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.firelord.weathering.core.data.Constants.CURRENT_WEATHER_ID

@Entity(tableName = "current_weather")
data class RemoteFetch(
    val base: String,
    val cod: Int,
    val dt: Int,
    val id: Int,
    val timezone: Int,
    val visibility: Int,
    val name: String,
    @Embedded(prefix = "clouds_")
    val clouds: Clouds,
    @Embedded(prefix = "coord_")
    val coord: Coord,
    @Embedded(prefix = "main_")
    val main: Main, // this contains the temp
    @Embedded(prefix = "sys_")
    val sys: Sys,
    val weather: List<Weather>,
    @Embedded(prefix = "wind_")
    val wind: Wind
) {
    @PrimaryKey(autoGenerate = false)
    var primaryKey: Int = CURRENT_WEATHER_ID
}