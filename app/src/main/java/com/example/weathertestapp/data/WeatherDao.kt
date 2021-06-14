package com.example.weathertestapp.data

import androidx.room.*
import com.example.weathertestapp.data.model.CurrentWeatherDB

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeatherDB: CurrentWeatherDB)

    @Update
    suspend fun updateCurrentWeather(currentWeatherDB: CurrentWeatherDB)

    @Query("SELECT * FROM current_weather_table")
    fun getCurrentWeather(): CurrentWeatherDB?

}