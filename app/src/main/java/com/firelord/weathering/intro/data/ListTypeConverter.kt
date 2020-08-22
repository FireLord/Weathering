package com.firelord.weathering.intro.data

import androidx.room.TypeConverter
import com.firelord.weathering.intro.data.response.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTypeConverter {
    @TypeConverter
    fun restoreList(listOfString: String?): List<Weather?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<Weather?>?>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<Weather?>?): String? {
        return Gson().toJson(listOfString)
    }
}