package com.example.weathertestapp.remote

import com.example.weathertestapp.data.model.CurrentWeatherResponse
import com.example.weathertestapp.data.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): CurrentWeatherResponse

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): ForecastResponse
}