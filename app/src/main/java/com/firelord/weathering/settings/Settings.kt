package com.firelord.weathering.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R
import com.firelord.weathering.databinding.ActivitySettingsBinding

class Settings : AppCompatActivity() {

    private lateinit var settingsActivity: ActivitySettingsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsActivity = ActivitySettingsBinding.inflate(layoutInflater)
        val view = settingsActivity.root
        setContentView(view)

        val dataList = ArrayList<Data>()
        dataList.add(
            Data(
                SettingsRecyclerAdapter.VIEW_TYPE_ONE,
                getString(R.string.darkModeInfo),
                getString(R.string.dark_mode),
                R.drawable.ic_dark_mode
            )
        )
        dataList.add(
            Data(
                SettingsRecyclerAdapter.VIEW_TYPE_TWO,
                getString(R.string.unitInfo),
                getString(R.string.unit),
                R.drawable.ic_square_foot
            )
        )
        dataList.add(
            Data(
                SettingsRecyclerAdapter.VIEW_TYPE_ONE,
                getString(R.string.locationInfo),
                getString(R.string.location),
                R.drawable.ic_place_add
            )
        )

        val adapter = SettingsRecyclerAdapter(this, dataList)
        recyclerView = settingsActivity.rvSettings
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}