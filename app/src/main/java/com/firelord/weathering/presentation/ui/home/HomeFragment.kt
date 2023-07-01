package com.firelord.weathering.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import com.firelord.weathering.data.util.Resource
import com.firelord.weathering.databinding.FragmentHomeBinding
import com.firelord.weathering.presentation.ui.DashboardActivity
import com.firelord.weathering.presentation.viewmodel.WeatherViewModel

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var viewModel: WeatherViewModel
    private var unit = "metric"
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

        viewModel = (activity as DashboardActivity).viewModel
        viewWeatherInfo()
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

    private fun viewWeatherInfo(){
        viewModel.location.observe(viewLifecycleOwner){location ->
            viewModel.getWeatherInfo(location, unit)
        }

        viewModel.weatherInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        homeBinding.tvTemp.text = it.main.temp.toString()
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(activity, "An error occured: $it", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    // TODO: use progressBar or skeleton loader
                    Toast.makeText(activity, "Loading", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}