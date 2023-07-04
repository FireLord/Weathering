package com.firelord.weathering.data.Repository.dataSource

import com.firelord.weathering.data.model.RemoteFetch

interface WeatherLocalDataSource {
    suspend fun saveWeatherToDB(remoteFetch: RemoteFetch)
}