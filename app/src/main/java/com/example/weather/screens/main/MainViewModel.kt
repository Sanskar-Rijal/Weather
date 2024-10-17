package com.example.weather.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.DataorException
import com.example.weather.model.Weather
import com.example.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository:WeatherRepository):ViewModel() {

    /**
    var data :MutableState<DataorException<Weather,Boolean,Exception>>
    = mutableStateOf(DataorException(null, true, Exception(""))) //creating a variable that is of correct type

    init {
        loadWeather()
    }
    private fun loadWeather(){
        getWeatherByCity("London")
    }
    private fun getWeatherByCity(cityName:String){
        viewModelScope.launch {

            if(cityName.isEmpty()) {
                return@launch
            }

            data.value.loading=true
            data.value = repository.getWeatherByCity(cityName)
            if(data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
            Log.d("Get", "getWeatherByCity:${data.value.data.toString()} ")
        }
    }
    **/
    suspend fun getWeatherData(city:String)
    :DataorException<Weather,Boolean,Exception>{
        Log.d("Get", "getWeatherData: ${repository.getWeatherByCity(city)}")
        return repository.getWeatherByCity(city = city)
    }
}