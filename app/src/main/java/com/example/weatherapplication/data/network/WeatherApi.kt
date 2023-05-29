package com.example.weatherapplication.data.network

import com.example.weatherapplication.models.Weather
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("current.json")
    suspend fun getTodayWeather(@Query("key") key: String, @Query("q") city: String):Weather

}