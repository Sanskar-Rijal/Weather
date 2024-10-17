package com.example.weather.model

import com.example.weather.MainActivity

data class WeatherItem(
    val dt:Int,
    val main:Main,
    val weather:List<WeatherX>,
    val clouds:Clouds,
    val wind:Wind,
    val visibility:Int,
    val pop:Double,
    val rain:Rain,
    val sys:System,
    val dt_txt:String
)
