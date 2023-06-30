package com.firelord.weathering.data.Repository.dataSourceImpl

import com.firelord.weathering.data.Repository.dataSource.WeatherRemoteDataSource
import com.firelord.weathering.data.api.WeatherAPIService
import com.firelord.weathering.data.model.RemoteFetch
import retrofit2.Response

class WeatherRemoteDataSourceImpl(
    private val weatherAPIService: WeatherAPIService
):WeatherRemoteDataSource {
    override suspend fun getWeatherInfo(location: String, unit: String): Response<RemoteFetch> {
        return weatherAPIService.getCurrentWeather(location,unit)
    }
}