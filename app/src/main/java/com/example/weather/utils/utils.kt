package com.example.weather.utils

import java.text.SimpleDateFormat

fun formatDate(timeStamp:Int):String{
    val simpdateformat= SimpleDateFormat("EEE, MMM d")
    val date = java.util.Date(timeStamp.toLong() * 1000)
    return simpdateformat.format(date)
}

//for showing temperature
fun formatDecimal(item:Double):String{
    return "%.0f".format(item) //after decimal it takes no value

}

//for showing sunrise and sunset
 fun formatTimeandDate(timeStamp:Int):String{
     val simpDateFormat = SimpleDateFormat( "hh:mm:aa" )
    val date= java.util.Date(timeStamp.toLong() *1000)
    return simpDateFormat.format(date)
 }


//for showing day ony
fun day(timeStamp:Int):String{
    val simpdateformat= SimpleDateFormat("EEE , MMM d, hh:mm a")
    val date = java.util.Date(timeStamp.toLong() * 1000)
    return simpdateformat.format(date)
}