package com.firelord.weathering.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R
import com.firelord.weathering.databinding.FragmentSettingsBinding

class Settings : Fragment() {

    private lateinit var settingsActivity: FragmentSettingsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsActivity = FragmentSettingsBinding.inflate(inflater)
        return settingsActivity.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*
        Create data list based on Data model
        viewType = simple_setting_row or setting_unit_row
        textInfo = explain what that setting is for
        textName = setting's name
        textIcon = setting's icon
        */
        val dataList = ArrayList<SettingsModel>()
        dataList.add(
            SettingsModel(
                SettingsRecyclerAdapter.VIEW_TYPE_ONE,
                getString(R.string.darkModeInfo),
                getString(R.string.dark_mode),
                R.drawable.ic_dark_mode
            )
        )
        dataList.add(
            SettingsModel(
                SettingsRecyclerAdapter.VIEW_TYPE_TWO,
                getString(R.string.unitInfo),
                getString(R.string.unit),
                R.drawable.ic_square_foot
            )
        )
        dataList.add(
            SettingsModel(
                SettingsRecyclerAdapter.VIEW_TYPE_ONE,
                getString(R.string.locationInfo),
                getString(R.string.location),
                R.drawable.ic_place_add
            )
        )

        val adapter = activity?.let { SettingsRecyclerAdapter(it, dataList) }
        recyclerView = settingsActivity.rvSettings
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }
}