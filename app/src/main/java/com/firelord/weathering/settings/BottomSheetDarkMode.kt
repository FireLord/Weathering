package com.firelord.weathering.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.firelord.weathering.R
import com.firelord.weathering.core.data.Constants
import com.firelord.weathering.core.data.Preferences
import com.firelord.weathering.databinding.BottomSheetDarkModeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDarkMode : BottomSheetDialogFragment() {

    private lateinit var darkModeActivity: BottomSheetDarkModeBinding

    private var darkListener: BottomSheetListener? = null
    private var preferences: Preferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        darkModeActivity = BottomSheetDarkModeBinding.inflate(inflater)
        return darkModeActivity.root
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

        darkModeActivity.radioGroup.setOnCheckedChangeListener { _, checkedId ->
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
            AppCompatDelegate.MODE_NIGHT_NO -> darkModeActivity.rbLighttheme.isChecked = true
            AppCompatDelegate.MODE_NIGHT_YES -> darkModeActivity.rbDarkTheme.isChecked = true
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> darkModeActivity.rbSystemTheme.isChecked =
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