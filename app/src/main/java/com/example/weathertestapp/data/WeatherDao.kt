package com.example.weathertestapp.data

import androidx.room.*
import com.example.weathertestapp.data.model.CurrentWeatherDB
import com.example.weathertestapp.data.model.ForecastDB

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeatherDB: CurrentWeatherDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(forecastDB: ForecastDB)

    @Query("SELECT * FROM current_weather_table")
    fun getCurrentWeather(): CurrentWeatherDB?

    @Query("SELECT * FROM forecast_table")
    fun getForecast(): ForecastDB?

}