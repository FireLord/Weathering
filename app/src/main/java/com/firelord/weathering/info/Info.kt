package com.firelord.weathering.info

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firelord.weathering.databinding.FragmentInfoBinding

class Info : Fragment() {

    private lateinit var infoActivity: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoActivity = FragmentInfoBinding.inflate(inflater)

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
        return infoActivity.root
    }
}