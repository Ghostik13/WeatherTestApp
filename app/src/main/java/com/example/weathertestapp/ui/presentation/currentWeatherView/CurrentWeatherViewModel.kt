package com.example.weathertestapp.ui.presentation.currentWeatherView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertestapp.domain.WeatherRepository
import com.example.weathertestapp.ui.presentation.mapper.toPresentationModel
import com.example.weathertestapp.ui.presentation.model.CurrentWeatherPresentation
import kotlinx.coroutines.*

class CurrentWeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<CurrentWeatherPresentation>()
    val weather: LiveData<CurrentWeatherPresentation> = _weather

    val city = MutableLiveData<String>()
    val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun getCurrentWeather() {
        city.observeForever {
            runBlocking {
                val weather = async { repository.getCurrentWeather(it) }
                _weather.postValue(weather.await().toPresentationModel())
                loading.value = true
            }
        }
//        viewModelScope.launch(Dispatchers.IO) {
//            val weatherDb = repository.getCurrentWeather()
//            withContext(Dispatchers.Main) {
//                _weather.value = weatherDb?.toPresentationModel()
//            }
//        }
    }
}