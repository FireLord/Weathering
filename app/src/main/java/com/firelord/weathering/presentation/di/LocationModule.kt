package com.firelord.weathering.presentation.di

import android.app.Application
import android.content.SharedPreferences
import android.location.Geocoder
import androidx.preference.PreferenceManager
import com.firelord.weathering.presentation.ui.settings.LocationClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocationModule {
    @Singleton
    @Provides
    fun provideFusedLocationClient(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }
    @Singleton
    @Provides
    fun provideGeocoder(application: Application): Geocoder {
        return Geocoder(application, Locale.getDefault())
    }
    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}