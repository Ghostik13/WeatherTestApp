package com.example.weathertestapp.remote

import com.example.weathertestapp.data.model.CurrentWeatherResponse
import com.example.weathertestapp.data.model.WeekWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") apiKey: String,
        @Query("appid") city: String
    ): CurrentWeatherResponse

    @GET("trending")
    suspend fun getWeekWeather(
        @Query("api_key") apiKey: String,
        @Query("q") city: String
    ): WeekWeatherResponse
}