package com.firelord.weathering.presentation.ui.intro

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.firelord.weathering.R
import com.firelord.weathering.data.util.Resource
import com.firelord.weathering.databinding.ActivityMainBinding
import com.firelord.weathering.presentation.ui.DashboardActivity
import com.firelord.weathering.presentation.viewmodel.WeatherViewModel
import com.firelord.weathering.presentation.viewmodel.WeatherViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    @Inject
    lateinit var factory: WeatherViewModelFactory
    lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        viewModel = ViewModelProvider(this,factory)[WeatherViewModel::class.java]

        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.edit().putBoolean("activityOpened", true).apply()

        mainBinding.textInputLayoutOutlined.setEndIconOnClickListener {
           // Set 'city' from user input
            val city = mainBinding.textInputEditTextOutlined.text.toString()
            if (city.isNotEmpty()) {
                checkCityName(city)
                viewModel.successBool.observe(this){successBool ->
                    if(successBool){
                        sharedPreferences.edit().putString("city", city).apply()
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                // if 'city' is empty report user with toast
                Toast.makeText(this, getString(R.string.str_no_loc), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkCityName(city:String){
        viewModel.getWeatherInfo(city, "metric")
        viewModel.weatherInfo.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        mainBinding.textInputLayoutOutlined.endIconDrawable =
                            ContextCompat.getDrawable(
                                this@MainActivity,
                                R.drawable.ic_check_circle
                            )
                    }
                    viewModel.successBool.postValue(true)
                }

                is Resource.Error -> {
                    response.message?.let {
                        mainBinding.textInputLayoutOutlined.error = it
                        /*
                        When user starts to fix his/her error bcz of location
                        not found change the error icon to arrow again and
                        remove material error ui
                        */
                        mainBinding.textInputEditTextOutlined.addTextChangedListener(
                            object :
                                TextWatcher {
                                override fun onTextChanged(
                                    s: CharSequence,
                                    start: Int,
                                    before: Int,
                                    count: Int
                                ) {
                                }

                                override fun beforeTextChanged(
                                    s: CharSequence,
                                    start: Int,
                                    count: Int,
                                    aft: Int
                                ) {
                                }

                                override fun afterTextChanged(s: Editable) {
                                    // Here material error ui is removed
                                    mainBinding.textInputLayoutOutlined.error = null
                                    // Here icon is changed to arrow
                                    mainBinding.textInputLayoutOutlined.endIconDrawable =
                                        ContextCompat.getDrawable(
                                            this@MainActivity,
                                            R.drawable.ic_arrow
                                        )
                                }
                            })
                        Toast.makeText(this, "An error occured: $it", Toast.LENGTH_LONG).show()
                    }
                    viewModel.successBool.postValue(false)
                }
                is Resource.Loading -> {
                    mainBinding.textInputLayoutOutlined.endIconDrawable =
                        ContextCompat.getDrawable(this, R.drawable.ic_check_circle_outline)
                }
            }
        }
    }
}
