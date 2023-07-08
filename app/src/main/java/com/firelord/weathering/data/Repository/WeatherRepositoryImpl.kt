package com.firelord.weathering.data.Repository

import com.firelord.weathering.data.Repository.dataSource.WeatherLocalDataSource
import com.firelord.weathering.data.Repository.dataSource.WeatherRemoteDataSource
import com.firelord.weathering.data.model.Main
import com.firelord.weathering.data.model.RemoteFetch
import com.firelord.weathering.data.util.Resource
import com.firelord.weathering.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource
): WeatherRepository {

    private fun responseToResource(response: Response<RemoteFetch>):Resource<RemoteFetch>{
        if (response.isSuccessful){
            response.body()?.let {result ->
                return Resource.Success(result)

            }
        }
        return Resource.Error(response.message(), response.code())
    }

    override suspend fun getWeatherInfo(location: String, unit: String): Resource<RemoteFetch> {
        return responseToResource(weatherRemoteDataSource.getWeatherInfo(location,unit))
    }

    override suspend fun getSearchedWeather(searchQuery: String): Resource<RemoteFetch> {
        TODO("Not yet implemented")
    }

    override suspend fun saveWeather(remoteFetch: RemoteFetch) {
        weatherLocalDataSource.saveWeatherToDB(remoteFetch)
    }

    override suspend fun deleteWeather(remoteFetch: RemoteFetch) {
        TODO("Not yet implemented")
    }

    override fun getSavedWeather(): Flow<List<RemoteFetch>> {
        TODO("Not yet implemented")
    }
}