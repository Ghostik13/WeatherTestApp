package com.example.weathertestapp.ui.presentation.model

data class ForecastPresentation(
    val city: String,
    val temp: Int,
    val time: String,
    val description: String,
    val icon: Int
)