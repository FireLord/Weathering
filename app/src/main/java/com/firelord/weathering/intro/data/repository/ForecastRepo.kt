package com.firelord.weathering.intro.data.repository

import androidx.lifecycle.LiveData
import com.firelord.weathering.intro.data.db.response.RemoteFetch

interface ForecastRepo {
    suspend fun getCurrentWeather(): LiveData<RemoteFetch>
}