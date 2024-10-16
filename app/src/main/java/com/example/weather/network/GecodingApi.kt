package com.example.weather.network

import com.example.weather.modelgeo.GeoLocation
import com.example.weather.modelgeo.GeoLocationItem
import com.example.weather.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface GecodingApi {
    @GET(value = "geo/1.0/direct")
    suspend fun getCoordinates(
        @Query(value = "q") city:String,
        @Query(value = "limit") limit:Int=1,
        @Query(value = "appid") appid:String=Constants.API_KEY
    ):GeoLocation
        //http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid={API key}

}
