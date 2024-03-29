package com.firelord.weathering.domain.repository

import com.firelord.weathering.data.model.Main
import com.firelord.weathering.data.model.RemoteFetch
import com.firelord.weathering.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherInfo(location: String, unit: String): Resource<RemoteFetch>
    suspend fun getSearchedWeather(searchQuery:String): Resource<RemoteFetch>
    suspend fun saveWeather(main: Main)
    suspend fun deleteWeather(main: Main)
    fun getSavedWeather():Flow<List<Main>>
}