package com.example.weathertestapp.ui.presentation.model

data class CurrentWeatherPresentation(
    val city: String,
    val temp: String,
    val humidity: Int,
    val rain: Double?,
    val pressure: Int,
    val windSpeed: String,
    val windDirection: String,
    val description: String,
    val icon: Int
)