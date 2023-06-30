package com.firelord.weathering.data.Repository.dataSource

import com.firelord.weathering.data.model.RemoteFetch
import retrofit2.Response

interface WeatherRemoteDataSource {
    suspend fun getWeatherInfo():Response<RemoteFetch>
}