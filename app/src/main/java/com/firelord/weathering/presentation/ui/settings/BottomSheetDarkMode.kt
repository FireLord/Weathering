package com.firelord.weathering.presentation.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.firelord.weathering.R
import com.firelord.weathering.data.Constants
import com.firelord.weathering.core.data.Preferences
import com.firelord.weathering.databinding.BottomSheetDarkModeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDarkMode : BottomSheetDialogFragment() {

    private lateinit var darkModeBinding: BottomSheetDarkModeBinding

    private var darkListener: BottomSheetListener? = null
    private var preferences: Preferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        darkModeBinding = BottomSheetDarkModeBinding.inflate(inflater)
        return darkModeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            preferences = Preferences(it)
        }

        preferences?.let {
            val currentThemeMode =
                it.fetchInt(Constants.KEY_THEME_MODE, AppCompatDelegate.MODE_NIGHT_NO)

            updateRadioChecked(currentThemeMode)
        }

        darkModeBinding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbLighttheme -> {
                    dismiss()
                    darkListener?.onButtonClicked(AppCompatDelegate.MODE_NIGHT_NO)
                }
                R.id.rbDarkTheme -> {
                    dismiss()
                    darkListener?.onButtonClicked(AppCompatDelegate.MODE_NIGHT_YES)
                }
                R.id.rbSystemTheme -> {
                    dismiss()
                    darkListener?.onButtonClicked(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        }
    }

    private fun updateRadioChecked(mode: Int) {
        when (mode) {
            AppCompatDelegate.MODE_NIGHT_NO -> darkModeBinding.rbLighttheme.isChecked = true
            AppCompatDelegate.MODE_NIGHT_YES -> darkModeBinding.rbDarkTheme.isChecked = true
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> darkModeBinding.rbSystemTheme.isChecked =
                true
        }
    }

    interface BottomSheetListener {
        fun onButtonClicked(mode: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            darkListener = context as BottomSheetListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement BottomSheetListener"
            )
        }
    }
}