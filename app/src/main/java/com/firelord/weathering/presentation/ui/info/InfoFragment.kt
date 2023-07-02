package com.firelord.weathering.presentation.ui.info

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firelord.weathering.BuildConfig
import com.firelord.weathering.R
import com.firelord.weathering.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private lateinit var infoBinding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoBinding = FragmentInfoBinding.inflate(inflater)
        return infoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Open lib activity on lib Button press
        infoBinding.buLibrary.setOnClickListener {
            val library = Intent(activity, LibraryActivity::class.java)
            startActivity(library)
        }

        // Open changelog sheet on changelog button press
        infoBinding.buChangelog.setOnClickListener {
            val bottomSheet = BottomSheetChangelog()
            activity?.supportFragmentManager?.let { it1 -> bottomSheet.show(it1, bottomSheet.tag) }
        }

        infoBinding.tvVersion.text = "${getString(R.string.str_version)} ${BuildConfig.VERSION_NAME}"
    }
}