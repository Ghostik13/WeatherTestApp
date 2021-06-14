package com.example.weathertestapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather_table")
data class CurrentWeatherDB(
    @PrimaryKey
    val id: Int,
    val timezone: Int,
    val city: String,
    val temp: Double,
    val humidity: Int,
    val rain: Double?,
    val pressure: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val description: String,
    val icon: String
)