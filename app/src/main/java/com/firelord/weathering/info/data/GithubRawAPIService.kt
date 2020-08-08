package com.firelord.weathering.info.data

import com.firelord.weathering.BuildConfig
import com.firelord.weathering.info.data.response.GhChangelog
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val versionName = BuildConfig.VERSION_NAME

interface GithubRawAPIService {
    @GET("/FireLord/WeatheringLog/master/${versionName}/changelog.json")
    fun getChangelogAdapter(): Call<GhChangelog>

    companion object{
        operator fun invoke(): GithubRawAPIService {
            val okHttpClient = OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubRawAPIService::class.java)
        }
    }
}
