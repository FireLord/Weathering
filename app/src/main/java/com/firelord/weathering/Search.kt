package com.firelord.weathering

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firelord.weathering.databinding.FragmentSearchBinding

class Search : Fragment() {

    private lateinit var searchActivity: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchActivity = FragmentSearchBinding.inflate(inflater)
        return searchActivity.root
    }
}