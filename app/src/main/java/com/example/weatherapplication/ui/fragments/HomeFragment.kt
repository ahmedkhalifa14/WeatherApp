package com.example.weatherapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.data.local.DataStoreManager
import com.example.weatherapplication.databinding.FragmentHomeBinding
import com.example.weatherapplication.models.Weather
import com.example.weatherapplication.ui.viewmodels.MainViewModel
import com.example.weatherapplication.utils.Constants.API_KEY
import com.example.weatherapplication.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getTodayWeather(API_KEY, dataStoreManager.userLocation.value.toString())
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        lifecycleScope.launchWhenStarted {
            launch {
                mainViewModel.weatherState.collect(
                    EventObserver(
                        onLoading = {
                            binding!!.spinkit.isVisible = true
                        },
                        onSuccess = { weather ->
                            binding!!.spinkit.isVisible = false
                            binding!!.detailsContainer.isVisible = true
                            displayWeather(weather)
                        },
                        onError = { error ->
                            binding!!.spinkit.isVisible = false
                            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                        }
                    )
                )
            }
        }
    }

    private fun displayWeather(weather: Weather) {
        binding!!.locationTv.text = weather.location.name
        binding!!.updatedAtTv.text = weather.current.last_updated
        binding!!.status.text = weather.current.condition.text
        binding!!.temp.text = weather.current.temp_c.toString() + "°C"
        binding!!.precipitationTv.text = weather.current.precip_in.toString() + "inch"
        binding!!.humidity.text = weather.current.humidity.toString() + "%"
        binding!!.windTv.text = weather.current.wind_kph.toString() + "k/h"
        binding!!.pressureTv.text = weather.current.pressure_mb.toString() +"mb"
        binding!!.cloudTv.text = weather.current.cloud.toString() + "%"
        var uvIndex: String
        when (weather.current.uv) {
            in 0.0..2.0 -> {
                uvIndex = "Low"
            }

            in 3.0..5.0 -> {
                uvIndex = "Moderate"
            }

            in 6.0..7.0 -> {
                uvIndex = "High"

            }

            in 8.0..10.0 -> {
                uvIndex = "Very High"
            }

            else -> {
                uvIndex = "Extreme"
            }
        }
        binding!!.uvIndexTv.text = uvIndex
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}