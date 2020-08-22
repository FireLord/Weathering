package com.firelord.weathering.intro.data.repository

import androidx.lifecycle.LiveData
import com.firelord.weathering.intro.data.db.CurrentWeatherDao
import com.firelord.weathering.intro.data.db.response.RemoteFetch

class ForcastRepoImpl(
    private val currentWeatherDao: CurrentWeatherDao
) : ForecastRepo {
    override suspend fun getCurrentWeather(): LiveData<RemoteFetch> {
        TODO("Not yet implemented")
    }
}