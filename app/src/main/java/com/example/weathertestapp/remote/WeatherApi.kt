package com.example.weathertestapp.remote

import com.example.weathertestapp.data.model.CurrentWeatherRemote
import com.example.weathertestapp.data.model.ForecastRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): CurrentWeatherRemote

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): ForecastRemote
}