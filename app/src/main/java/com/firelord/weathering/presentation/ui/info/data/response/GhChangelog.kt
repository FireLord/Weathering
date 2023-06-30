package com.firelord.weathering.presentation.ui.info.data.response

import com.google.gson.annotations.SerializedName

data class GhChangelog(
    @SerializedName("logs")
    val logs: List<String>
)