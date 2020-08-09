package com.firelord.weathering.info

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firelord.weathering.BuildConfig
import com.firelord.weathering.R
import com.firelord.weathering.databinding.FragmentInfoBinding

class Info : Fragment() {

    private lateinit var infoActivity: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoActivity = FragmentInfoBinding.inflate(inflater)
        return infoActivity.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Open conti activity on conti Button press
        infoActivity.buConti.setOnClickListener{
            val conti = Intent(activity, Contributors::class.java)
            startActivity(conti)
        }

        // Open lib activity on lib Button press
        infoActivity.buLibrary.setOnClickListener{
            val library = Intent(activity, Library::class.java)
            startActivity(library)
        }

        // Open changelog sheet on changelog button press
        infoActivity.buChangelog.setOnClickListener {
            val bottomSheet = BottomSheetChangelog()
            activity?.supportFragmentManager?.let { it1 -> bottomSheet.show(it1,bottomSheet.tag) }
        }

        infoActivity.tvVersion.text="${getString(R.string.version)} ${BuildConfig.VERSION_NAME}"
    }
}