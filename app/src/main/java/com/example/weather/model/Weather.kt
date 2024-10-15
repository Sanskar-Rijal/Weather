package com.example.weather.model

data class Weather(
    val cod:Int,
    val message:Int,
    val cnt:Int,
    val list:List<WeatherItem>,
    val city:City
)
