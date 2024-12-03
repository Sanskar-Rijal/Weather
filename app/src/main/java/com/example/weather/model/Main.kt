package com.example.weather.model

data class Main(
    var temp:Double,
    val feels_like:Double,
    val temp_min:Double,
    val temp_max:Double,
    val pressure:Int,
    val sea_level:Int,
    val grnd_level:Int,
    val humidity:Int,
    val temp_kf:Double
)
