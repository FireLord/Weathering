package com.firelord.weathering.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firelord.weathering.databinding.BottomSheetDarkModeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDarkMode: BottomSheetDialogFragment() {

    private lateinit var DarkModeActivity: BottomSheetDarkModeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DarkModeActivity = BottomSheetDarkModeBinding.inflate(inflater)
        return DarkModeActivity.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}