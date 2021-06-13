package com.example.weathertestapp.ui.presentation.model

data class CurrentWeatherPresentation(
    val temp: Double,
    val humidity: Int,
    val rain: Double,
    val pressure: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val description: String
)