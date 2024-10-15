package com.example.weather.model

data class Weather(
    val city:City,
    val cod:Int,
    val cnt:Int,
    val list:List<WeatherItem>,
    val message:Double
)
