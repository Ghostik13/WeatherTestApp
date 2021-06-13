package com.example.weathertestapp.ui.presentation.currentWeatherView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.ui.presentation.mapper.toPresentationModel
import com.example.weathertestapp.ui.presentation.model.CurrentWeatherPresentation
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(private val repository: WeatherRepository): ViewModel() {

    private val _weather = MutableLiveData<CurrentWeatherPresentation>()
    val weather: LiveData<CurrentWeatherPresentation> = _weather

    var city = MutableLiveData<String>()

    fun getCurrentWeather() {
        city.observeForever {
            viewModelScope.launch {
                val weather = repository.getCurrentWeather(it)
                _weather.value = weather.toPresentationModel()
            }
        }
    }

}