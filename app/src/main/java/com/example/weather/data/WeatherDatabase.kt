package com.example.weather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.model.Favorites
import com.example.weather.model.Unit


@Database(entities = [Favorites::class,Unit::class], version = 2 , exportSchema = false)//here we setup skeleton of our database
abstract class WeatherDatabase:RoomDatabase() {
    abstract fun weatherDao():WeatherDao

}