package com.firelord.weathering.info

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
        return infoActivity.root
    }
}