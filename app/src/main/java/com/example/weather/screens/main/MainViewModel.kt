package com.example.weather.screens.main

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

    var data :MutableState<DataorException<Weather,Boolean,Exception>>
    = mutableStateOf(DataorException(null, true, Exception(""))) //creating a variable that is of correct type

    init {
        loadWeather()
    }
    private fun loadWeather(){
        getWeather(44.34,10.99)
    }
    private fun getWeather(latitude:Double, longitude:Double){
        viewModelScope.launch {
            if(latitude.toString().isEmpty() || longitude.toString().isEmpty()) {
                return@launch
            }
            data.value.loading=true
            data.value=repository.getWeather(latitude,longitude)
            if(data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }
    }
}