package com.example.weather.repository

import android.provider.ContactsContract.Data
import android.util.Log
import com.example.weather.data.DataorException
import com.example.weather.model.Weather
import com.example.weather.modelgeo.GeoLocation
import com.example.weather.network.GecodingApi
import com.example.weather.network.WeatherApi
import javax.inject.Inject

//similar to taking data from dao but in this case Weather_Api
class WeatherRepository @Inject constructor(
    private val gecoding_api:GecodingApi,
    private val weather_api:WeatherApi) {

    //getting weather by city name
    suspend fun getWeatherByCity(city:String):DataorException<Weather,Boolean,Exception>{
        try {
            val locationlist:GeoLocation= gecoding_api.getCoordinates(city)
            if (locationlist.isNotEmpty()){
                val location = locationlist.first()
                return getWeather(location.lat,location.lon)
            }
            return DataorException(e=Exception("City not found"))
        }catch (e:Exception){
            return DataorException(e=e)
        }
    }




    suspend fun getWeather(latitude:Double, longitude:Double):DataorException<Weather,Boolean,Exception> {
        val response = try{
            weather_api.getWeather(latitude,longitude)
        }catch (e:Exception){
            Log.d("Rex", "getWeather: $e")
            return DataorException(e=e)
        }
        return DataorException(data=response)
    }
}