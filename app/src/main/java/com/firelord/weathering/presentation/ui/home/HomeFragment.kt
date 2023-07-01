package com.firelord.weathering.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.firelord.weathering.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* // Set data from dashBoardActivity
        homeBinding.tvTemp.text = arguments?.getString("tvTemp")
        homeBinding.tvRain.text = arguments?.getString("tvRain")
        homeBinding.tvHumidity.text = arguments?.getString("tvHumidity")
        homeBinding.tvWind.text = arguments?.getString("tvWindSpeed")
        homeBinding.tvWeatherType.text = arguments?.getString("tvWeatherType")
        homeBinding.tvDate.text = arguments?.getString("tvDate")
        homeBinding.tvLocation.text = arguments?.getString("tvLocation")
        homeBinding.ivBgWeather.setImageDrawable(activity?.let { ContextCompat.getDrawable(it,arguments?.getInt("ivBgWeather")!!) })
        homeBinding.tvRainName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, arguments?.getInt("tvRainName")!!, 0, 0
        )
        homeBinding.tvHumidityName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, arguments?.getInt("tvHumidityName")!!, 0, 0
        )
        homeBinding.tvWindName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, arguments?.getInt("tvWindName")!!, 0, 0
        )
        homeBinding.tvWeatherType.setTextColor(activity?.let {
            getColor(
                it,
                arguments?.getInt("tvWeatherTypeColor")!!
            )
        }!!)*/
    }
}