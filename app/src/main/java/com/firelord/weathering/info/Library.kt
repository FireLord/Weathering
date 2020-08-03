package com.firelord.weathering.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firelord.weathering.R
import com.firelord.weathering.databinding.ActivityLibraryBinding

class Library : AppCompatActivity() {

    private lateinit var libraryActivity: ActivityLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        libraryActivity = ActivityLibraryBinding.inflate(layoutInflater)
        val view = libraryActivity.root
        setContentView(view)
    }
}