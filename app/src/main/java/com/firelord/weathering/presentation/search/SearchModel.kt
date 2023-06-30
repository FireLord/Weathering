package com.firelord.weathering.presentation.search

import android.graphics.drawable.Drawable

data class SearchModel(
    val textCity: String,
    val textCondition: String,
    val textTemp: String,
    val textImage: Drawable?
)