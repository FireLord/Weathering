package com.firelord.weathering.data.db

import androidx.room.TypeConverter
import com.firelord.weathering.data.model.Clouds
import com.firelord.weathering.data.model.Main
import com.firelord.weathering.data.model.Sys
import com.firelord.weathering.data.model.Weather
import com.firelord.weathering.data.model.Wind
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun toCloudsString(clouds: Clouds?): String? {
        return gson.toJson(clouds)
    }

    @TypeConverter
    fun toCloudsObject(cloudsString: String?): Clouds? {
        return gson.fromJson(cloudsString, Clouds::class.java)
    }

    @TypeConverter
    fun toMainString(main: Main?): String? {
        return gson.toJson(main)
    }

    @TypeConverter
    fun toMainObject(mainString: String?): Main? {
        return gson.fromJson(mainString, Main::class.java)
    }

    @TypeConverter
    fun toSysString(sys: Sys?): String? {
        return gson.toJson(sys)
    }

    @TypeConverter
    fun toSysObject(sysString: String?): Sys? {
        return gson.fromJson(sysString, Sys::class.java)
    }

    @TypeConverter
    fun toWeatherListString(weatherList: List<Weather>): String? {
        return gson.toJson(weatherList)
    }

    @TypeConverter
    fun toWeatherListObject(weatherListString: String?): List<Weather> {
        val listType = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(weatherListString, listType)
    }

    @TypeConverter
    fun toWindString(wind: Wind?): String? {
        return gson.toJson(wind)
    }

    @TypeConverter
    fun toWindObject(windString: String?): Wind? {
        return gson.fromJson(windString, Wind::class.java)
    }
}