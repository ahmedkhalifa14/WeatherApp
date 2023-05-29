package com.example.weatherapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.models.Weather
import com.example.weatherapplication.repo.ApiRepoImpl
import com.example.weatherapplication.utils.Event
import com.example.weatherapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: ApiRepoImpl
) : ViewModel() {
    private val _weatherState =
        MutableStateFlow<Event<Resource<Weather>>>(Event(Resource.Init()))
    val weatherState: MutableStateFlow<Event<Resource<Weather>>> =
        _weatherState

    fun getTodayWeather(key: String, city: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _weatherState.emit(Event(Resource.Loading()))
            val result = repo.getTodayWeather(key, city)
            _weatherState.emit(Event(result))
        }
    }
}