package com.example.weatherapplication.repo

import com.example.weatherapplication.models.Weather
import com.example.weatherapplication.utils.Resource
import retrofit2.http.Query


interface ApiRepo {
    suspend fun getTodayWeather( key: String,city: String): Resource<Weather>

}