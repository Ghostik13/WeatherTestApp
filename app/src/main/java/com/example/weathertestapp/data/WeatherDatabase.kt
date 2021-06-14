package com.example.weathertestapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathertestapp.data.model.CurrentWeatherDB

@Database(entities = [CurrentWeatherDB::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao
}