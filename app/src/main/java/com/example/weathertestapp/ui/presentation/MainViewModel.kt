package com.example.weathertestapp.ui.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.weathertestapp.data.model.ForecastRemote
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.ui.presentation.mapper.toPresentation
import com.example.weathertestapp.ui.presentation.mapper.toPresentationModel
import com.example.weathertestapp.ui.presentation.model.CurrentWeatherPresentation
import com.example.weathertestapp.ui.presentation.model.ForecastPresentation
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<CurrentWeatherPresentation>()
    val weather: LiveData<CurrentWeatherPresentation> = _weather

    companion object {
        private val _city = MutableLiveData<String>()
    }

    val city: LiveData<String> = _city

    val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun initCity(name: String) {
        _city.value = name
    }

    suspend fun getCurrentWeather(name: String) {
        try {
            repository.getCurrentWeather(name).toPresentationModel().let {
                _weather.postValue(it)
            }
            withContext(Dispatchers.Main) {
                loading.value = true
            }
        } catch (e: Exception) {
            Log.d("INTERNET", "LOST")
            _weather.postValue(
                repository.getCurrentWeather()?.toPresentationModel()
            )
        }
    }

    fun getCurrentWeatherDb() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCurrentWeather()?.toPresentationModel()?.let {
                _weather.postValue(it)
            }
        }
    }

    private val _forecast = MutableLiveData<ForecastPresentation>()
    val forecast: LiveData<ForecastPresentation> = _forecast

    fun getForecastDb() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getForecast()?.toPresentation()?.let {
                _forecast.postValue(it)
            }
        }
    }

    suspend fun getForecast(name: String) {
        try {
            repository.getForecast(name).toPresentation().let {
                _forecast.postValue(it)
            }
            withContext(Dispatchers.Main) {
                loading.value = true
            }
        } catch (e: Exception) {
            Log.d("INTERNET", "LOST")
            _forecast.postValue(
                repository.getForecast()?.toPresentation()
            )
        }
    }
}

