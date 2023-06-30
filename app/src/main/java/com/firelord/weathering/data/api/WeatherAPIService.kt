package com.firelord.weathering.data.api

import com.firelord.weathering.data.Constants.API_KEY
import com.firelord.weathering.data.model.RemoteFetch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.openweathermap.org/data/2.5/weather?q=Noida&appid=8013e6a72812262e6b07a40357a7549d

interface WeatherAPIService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q")
        location: String,
        @Query("units")
        unit: String,
        @Query("appid")
        apiKey:String = API_KEY
    ): Response<RemoteFetch>
}