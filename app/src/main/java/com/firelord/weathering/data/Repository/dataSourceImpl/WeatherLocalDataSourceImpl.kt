package com.firelord.weathering.data.Repository.dataSourceImpl

import com.firelord.weathering.data.Repository.dataSource.WeatherLocalDataSource
import com.firelord.weathering.data.db.RemoteFetchDao
import com.firelord.weathering.data.model.RemoteFetch

class WeatherLocalDataSourceImpl(
    private val remoteFetchDao: RemoteFetchDao
): WeatherLocalDataSource {
    override suspend fun saveWeatherToDB(remoteFetch: RemoteFetch) {
        remoteFetchDao.insert(remoteFetch)
    }
}