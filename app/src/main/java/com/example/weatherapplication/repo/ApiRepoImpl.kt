package com.example.weatherapplication.repo

import com.example.weatherapplication.data.network.WeatherApi
import com.example.weatherapplication.models.Weather
import com.example.weatherapplication.utils.Resource
import com.example.weatherapplication.utils.Utils.tryCatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepoImpl
@Inject constructor(private val apiService: WeatherApi) : ApiRepo {
    override suspend fun getTodayWeather(key: String, city: String): Resource<Weather> =
        withContext(Dispatchers.IO) {
            tryCatch {
                val result = apiService.getTodayWeather(key, city)
                Resource.Success(result)
            }
        }


}