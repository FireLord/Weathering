package com.firelord.weathering.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firelord.weathering.databinding.BottomSheetChangelogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetChangelog : BottomSheetDialogFragment() {

    private lateinit var changelogActivity: BottomSheetChangelogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changelogActivity = BottomSheetChangelogBinding.inflate(inflater)
        return changelogActivity.root
    }
}