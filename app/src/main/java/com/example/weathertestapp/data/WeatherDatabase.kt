package com.example.weathertestapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weathertestapp.data.model.CurrentWeatherDB
import com.example.weathertestapp.data.model.ForecastConverter
import com.example.weathertestapp.data.model.ForecastDB

@Database(entities = [CurrentWeatherDB::class, ForecastDB::class], version = 1)
@TypeConverters(ForecastConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao
}