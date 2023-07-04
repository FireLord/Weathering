package com.firelord.weathering.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "remote_fetch"
)
data class RemoteFetch(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val dt: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val main: Main, // this contains the temp
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
):Serializable