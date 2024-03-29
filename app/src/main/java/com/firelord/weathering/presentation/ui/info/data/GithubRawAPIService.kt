package com.firelord.weathering.presentation.ui.info.data

import com.firelord.weathering.BuildConfig
import com.firelord.weathering.data.Constants.CHANGELOG_API_URL
import com.firelord.weathering.presentation.ui.info.data.response.GhChangelog
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val versionName = BuildConfig.VERSION_NAME

interface GithubRawAPIService {
    @GET("/FireLord/WeatheringLog/master/$versionName/changelog.json")
    fun getChangelogAdapter(): Call<GhChangelog>

    companion object {
        operator fun invoke(): GithubRawAPIService {
            val okHttpClient = OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(CHANGELOG_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubRawAPIService::class.java)
        }
    }
}
