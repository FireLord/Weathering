package com.firelord.weathering.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object Utilities {

    fun checkNetwork(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        val connected =
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        return connected
    }
}