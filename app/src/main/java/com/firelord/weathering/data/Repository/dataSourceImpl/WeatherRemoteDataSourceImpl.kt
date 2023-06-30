package com.firelord.weathering.data.Repository.dataSourceImpl

import com.firelord.weathering.data.Repository.dataSource.WeatherRemoteDataSource
import com.firelord.weathering.data.api.WeatherAPIService
import com.firelord.weathering.data.model.RemoteFetch
import retrofit2.Response

class WeatherRemoteDataSourceImpl(
    private val weatherAPIService: WeatherAPIService,
    private val location: String,
    private val unit: String
):WeatherRemoteDataSource {
    override suspend fun getWeatherInfo(): Response<RemoteFetch> {
        return weatherAPIService.getCurrentWeather(location,unit)
    }
}