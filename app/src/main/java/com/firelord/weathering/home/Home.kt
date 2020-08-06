package com.firelord.weathering.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.firelord.weathering.databinding.FragmentHomeBinding

class Home : Fragment() {

    private lateinit var homeActivity: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Set data from dashBoardActivity
        homeActivity = FragmentHomeBinding.inflate(inflater)
        homeActivity.tvTemp.text = arguments?.getString("tvTemp")
        homeActivity.tvRain.text = arguments?.getString("tvRain")
        homeActivity.tvHumidity.text = arguments?.getString("tvHumidity")
        homeActivity.tvWind.text = arguments?.getString("tvWindSpeed")
        homeActivity.tvWeatherType.text = arguments?.getString("tvWeatherType")
        homeActivity.tvDate.text = arguments?.getString("tvDate")
        homeActivity.tvLocation.text = arguments?.getString("tvLocation")
        homeActivity.clCard.setBackgroundResource(arguments?.getInt("clCard")!!)
        homeActivity.tvRainName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, arguments?.getInt("tvRainName")!!, 0, 0
        )
        homeActivity.tvHumidityName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, arguments?.getInt("tvHumidityName")!!, 0, 0
        )
        homeActivity.tvWindName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, arguments?.getInt("tvWindName")!!, 0, 0
        )
        homeActivity.tvWeatherType.setTextColor(activity?.let {
            getColor(
                it,
                arguments?.getInt("tvWeatherTypeColor")!!
            )
        }!!)
        return homeActivity.root
    }
}