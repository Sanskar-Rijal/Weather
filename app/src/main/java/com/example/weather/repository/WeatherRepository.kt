package com.example.weather.repository

import com.example.weather.data.DataorException
import com.example.weather.model.Weather
import com.example.weather.network.WeatherApi
import javax.inject.Inject

//similar to taking data from dao but in this case Weather_Api
class WeatherRepository @Inject constructor(
    private val api:WeatherApi) {

    suspend fun getWeather(latitude:Double, longitude:Double):DataorException<Weather,Boolean,Exception> {
        val response = try{
            api.getWeather(latitude,longitude)
        }catch (e:Exception){
            return DataorException(e=e)
        }
        return DataorException(data=response)
    }
}