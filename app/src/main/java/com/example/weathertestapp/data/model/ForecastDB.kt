package com.example.weathertestapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_table")
data class ForecastDB(
    @PrimaryKey
    val id: Int,
    val city: String,
    val temp: Double,
    val description: String,
    val icon: String
)