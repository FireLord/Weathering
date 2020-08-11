package com.firelord.weathering.core.data

import android.content.Context
import androidx.preference.PreferenceManager

class Preferences(context: Context) {

    private val preference = PreferenceManager.getDefaultSharedPreferences(context)

    fun storeInt(key: String, data: Int) {
        preference.edit()
            .putInt(key, data)
            .apply()
    }

    fun fetchInt(key: String, defaultValue: Int = 0): Int {
        return preference.getInt(key, defaultValue)
    }

    fun storeString(key: String, data: String) {
        preference.edit()
            .putString(key, data)
            .apply()
    }

    fun fetchString(key: String, defaultValue: String = ""): String {
        return preference.getString(key, defaultValue) ?: defaultValue
    }

    fun storeBoolean(key: String, data: Boolean) {
        preference.edit()
            .putBoolean(key, data)
            .apply()
    }

    fun fetchBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return preference.getBoolean(key, defaultValue)
    }

    companion object {
        @Volatile
        private var INSTANCE: Preferences? = null

        @JvmStatic
        fun getInstance(context: Context): Preferences {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            val instance = Preferences(context)
            INSTANCE = instance
            return instance
        }
    }
}