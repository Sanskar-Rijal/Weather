package com.example.weather.network

import com.example.weather.model.Weather
import com.example.weather.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    //using retrofit
    @GET(value ="data/2.5/forecast")
    //api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}
    suspend fun getWeather(
        @Query(value = "lat") latitude: Double,
        @Query(value = "lon") longitude: Double,
        @Query(value = "appid") appid:String =Constants.API_KEY
    ):Weather
}