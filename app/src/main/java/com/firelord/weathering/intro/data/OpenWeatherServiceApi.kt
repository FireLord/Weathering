package com.firelord.weathering.intro.data

import com.firelord.weathering.intro.data.response.RemoteFetch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "8013e6a72812262e6b07a40357a7549d"

//https://api.openweathermap.org/data/2.5/weather?q=Noida&appid=8013e6a72812262e6b07a40357a7549d

interface OpenWeatherServiceApi {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("units") unit: String
    ): Call<RemoteFetch>

    companion object {
        operator fun invoke(): OpenWeatherServiceApi {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(
                        "appid",
                        API_KEY
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
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherServiceApi::class.java)
        }
    }
}