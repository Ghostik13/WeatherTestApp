package com.example.weathertestapp.domain.model

data class CurrentWeatherDomain(
    val id: Int,
    val timezone: Int,
    val city: String,
    val temp: Double,
    val humidity: Int,
//    val rain: Double,
    val pressure: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val description: String
)