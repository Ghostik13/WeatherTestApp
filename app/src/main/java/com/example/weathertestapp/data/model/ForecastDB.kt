package com.example.weathertestapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "forecast_table")
data class ForecastDB(
    @PrimaryKey
    val id: Int,
    val city: String,
    @TypeConverters(ForecastConverter::class)
    val list: List<ModelDb>
)

data class ModelDb(
    val dt_txt: String,
    val temp: Double,
    val description: String,
    val icon: String
)





