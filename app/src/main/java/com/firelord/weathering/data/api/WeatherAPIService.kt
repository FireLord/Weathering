package com.firelord.weathering.data.api

import com.firelord.weathering.data.Constants
import com.firelord.weathering.data.model.RemoteFetch
import com.firelord.weathering.domain.repository.WeatherRepositoryTemp
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("units") unit: String
    ): Call<RemoteFetch>

    companion object {
        operator fun invoke(): WeatherRepositoryTemp {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(
                        "appid",
                        Constants.API_KEY
                    )
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.WEATHER_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherRepositoryTemp::class.java)
        }
    }
}