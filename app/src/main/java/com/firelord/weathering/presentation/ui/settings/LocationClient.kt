package com.firelord.weathering.presentation.ui.settings

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import javax.inject.Inject

class LocationClient
    @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val geocoder: Geocoder,
    private val sharedPreferences: SharedPreferences
) {

    @SuppressLint("MissingPermission")
    fun getLastLocation(onLocationSuccessHandler: () -> Unit) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    val addresses: List<Address>? = geocoder.getFromLocation(
                        location!!.latitude, location.longitude, 2
                    )

                    val cityName = addresses?.get(0)?.locality

                    sharedPreferences.edit().putString("city", cityName).apply()
                    onLocationSuccessHandler.invoke()
                }
                .addOnFailureListener {
                    Log.e("fusedLocation", it.message.toString())
                }
        }
    }