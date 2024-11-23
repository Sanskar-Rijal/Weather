package com.example.weather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.model.Favorites


@Database(entities = [Favorites::class], version = 1 , exportSchema = false)//here we setup skeleton of our database
abstract class WeatherDatabase:RoomDatabase() {
    abstract fun weatherDao():WeatherDao

}