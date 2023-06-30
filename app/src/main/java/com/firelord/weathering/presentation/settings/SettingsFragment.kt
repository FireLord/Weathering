package com.firelord.weathering.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firelord.weathering.R
import com.firelord.weathering.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var settingsBinding: FragmentSettingsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsBinding = FragmentSettingsBinding.inflate(inflater)
        return settingsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                getString(R.string.str_dark_mode_info),
                getString(R.string.str_dark_mode),
                R.drawable.ic_dark_mode
            )
        )
        dataList.add(
            SettingsModel(
                SettingsRecyclerAdapter.VIEW_TYPE_TWO,
                getString(R.string.str_unit_info),
                getString(R.string.str_unit),
                R.drawable.ic_square_foot
            )
        )
        dataList.add(
            SettingsModel(
                SettingsRecyclerAdapter.VIEW_TYPE_ONE,
                getString(R.string.str_location_info),
                getString(R.string.str_location),
                R.drawable.ic_place_add
            )
        )

        val adapter = activity?.let { SettingsRecyclerAdapter(it, dataList) }
        recyclerView = settingsBinding.rvSettings
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }
}