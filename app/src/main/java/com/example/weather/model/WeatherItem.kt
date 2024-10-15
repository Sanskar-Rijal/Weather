package com.example.weather.model

data class WeatherItem(
    val dt:Int,
    val sunrise:Int,
    val sunset:Int,
    val temp:Temp,
    val feels_like:Feelslike,
    val pressure:Int,
    val humidity:Int,
    val weather: List<WeatherObjects>,
    val speed:Double,
    val deg:Int,
    val gust:Double,
    val clouds:Int,
    val pop:Double,
    val rain:Double
)

