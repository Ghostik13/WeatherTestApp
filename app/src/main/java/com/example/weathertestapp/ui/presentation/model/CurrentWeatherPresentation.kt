package com.example.weathertestapp.ui.presentation.model

data class CurrentWeatherPresentation(
    val temp: Int,
    val humidity: Int,
    val rain: Double?,
    val pressure: Int,
    val windSpeed: Int,
    val windDirection: String,
    val description: String
)