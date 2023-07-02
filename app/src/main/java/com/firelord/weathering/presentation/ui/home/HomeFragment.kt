package com.firelord.weathering.presentation.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.firelord.weathering.R
import com.firelord.weathering.data.model.RemoteFetch
import com.firelord.weathering.data.util.Resource
import com.firelord.weathering.databinding.FragmentHomeBinding
import com.firelord.weathering.presentation.ui.DashboardActivity
import com.firelord.weathering.presentation.viewmodel.WeatherViewModel
import java.util.Calendar

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var viewModel: WeatherViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as DashboardActivity).viewModel
        viewWeatherInfo()

        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(requireContext())
        val unitValue = sharedPreferences.getString("unitValue", "metric")
        when (unitValue) {
            "metric" -> {
                viewModel.weatherUnit.value = "metric"
            }
            "imperial" -> {
                viewModel.weatherUnit.value = "imperial"
            }
        }

        val locationValue = sharedPreferences.getString("city",viewModel.location.value)
        viewModel.location.value = locationValue
    }

    private fun viewWeatherInfo(){
        viewModel.location.observe(viewLifecycleOwner){location ->
            viewModel.weatherUnit.observe(viewLifecycleOwner) { unit ->
                viewModel.getWeatherInfo(location, unit)
            }
        }

        viewModel.weatherInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        setData(it)
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(activity, "An error occured: $it", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    // TODO: use progressBar or skeleton loader
                }
            }

        }
    }

    // Use deg provided by api to set wind direction
    fun getWindDirection(deg: Int): String {
        return when {
            deg > 337.5 -> "North"
            deg > 292.5 -> "North West"
            deg > 247.5 -> "West"
            deg > 202.5 -> "South West"
            deg > 157.5 -> "South"
            deg > 122.5 -> "South East"
            deg > 67.5 -> "East"
            deg > 22.5 -> "North East"
            else -> "North"
        }
    }

    // Set month name from calender util
    fun monthName(month: Int): String {
        return when (month) {
            0 -> "Jan"
            1 -> "Feb"
            2 -> "Mar"
            3 -> "Apr"
            4 -> "May"
            5 -> "June"
            6 -> "July"
            7 -> "Aug"
            8 -> "Sep"
            9 -> "Oct"
            10 -> "Nov"
            11 -> "Dec"
            else -> "error"
        }
    }

    // Set date using various calendar output
    val date: String? = "${monthName(
        Calendar.getInstance()
            .get(Calendar.MONTH)
    )} ${
        Calendar.getInstance()
        .get(Calendar.DATE)}, ${
        Calendar.getInstance()
        .get(Calendar.YEAR)}"

    @SuppressLint("SetTextI18n")
    private fun setData(it:RemoteFetch) {
        val weatherNumber = it.weather[0]
        homeBinding.tvTemp.text = it.main.temp.toInt().toString() + "°"
        homeBinding.tvRain.text = it.clouds.all.toString() + "%"
        homeBinding.tvHumidity.text = it.main.humidity.toString() + "%"
        homeBinding.tvWind.text = it.wind.speed.toString() + "ms"
        homeBinding.tvWeatherType.text = weatherNumber.main
        homeBinding.tvDate.text = date
        homeBinding.tvLocation.text = "${it.name}, ${it.sys.country}"
        when (weatherNumber.id) {
            in 600..622 -> {
                // Snow
                homeBinding.ivBgWeather.setImageResource(R.drawable.snow)
                homeBinding.tvRainName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, R.drawable.ic_snow_rain, 0, 0
                )
                homeBinding.tvHumidityName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, R.drawable.ic_snow_hum, 0, 0
                )
                homeBinding.tvWindName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, R.drawable.ic_snow_wind, 0, 0
                )
                homeBinding.tvWeatherType.setTextColor(activity?.let {
                    getColor(
                        it,
                        R.color.colorSnowFont
                    )
                }!!)
            }

            in 500..531, in 300..321, in 200..232, 701, in 803..804 -> {
                // rain
                homeBinding.ivBgWeather.setImageResource(R.drawable.rain)
                homeBinding.tvRainName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, R.drawable.ic_rain_rain, 0, 0
                )
                homeBinding.tvHumidityName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, R.drawable.ic_rain_hum, 0, 0
                )
                homeBinding.tvWindName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, R.drawable.ic_rain_wind, 0, 0
                )
                homeBinding.tvWeatherType.setTextColor(activity?.let {
                    getColor(
                        it,
                        R.color.colorRainFont
                    )
                }!!)
            }

            else -> {
                // Sunny & others
                homeBinding.ivBgWeather.setImageResource(R.drawable.sunny)
                homeBinding.tvRainName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, R.drawable.ic_sunny_rain, 0, 0
                )
                homeBinding.tvHumidityName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, R.drawable.ic_sunny_hum, 0, 0
                )
                homeBinding.tvWindName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, R.drawable.ic_sunny_wind, 0, 0
                )
                homeBinding.tvWeatherType.setTextColor(activity?.let {
                    getColor(
                        it,
                        R.color.colorSunnyFont
                    )
                }!!)
            }
        }
    }
}