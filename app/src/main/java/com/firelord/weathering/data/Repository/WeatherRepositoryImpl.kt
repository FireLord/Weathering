package com.firelord.weathering.data.Repository

import com.firelord.weathering.data.Repository.dataSource.WeatherRemoteDataSource
import com.firelord.weathering.data.model.Main
import com.firelord.weathering.data.model.RemoteFetch
import com.firelord.weathering.data.util.Resource
import com.firelord.weathering.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
): WeatherRepository {

    private fun responseToResource(response: Response<RemoteFetch>):Resource<RemoteFetch>{
        if (response.isSuccessful){
            response.body()?.let {result ->
                return Resource.Success(result)

            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getWeatherInfo(location: String, unit: String): Resource<RemoteFetch> {
        return responseToResource(weatherRemoteDataSource.getWeatherInfo(location,unit))
    }

    override suspend fun getSearchedWeather(searchQuery: String): Resource<RemoteFetch> {
        TODO("Not yet implemented")
    }

    override suspend fun saveWeather(main: Main) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWeather(main: Main) {
        TODO("Not yet implemented")
    }

    override fun getSavedWeather(): Flow<List<Main>> {
        TODO("Not yet implemented")
    }
}