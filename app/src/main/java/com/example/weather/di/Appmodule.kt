package com.example.weather.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weather.data.WeatherDao
import com.example.weather.data.WeatherDatabase
import com.example.weather.network.GecodingApi
import com.example.weather.network.WeatherApi
import com.example.weather.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module //adding because it's dagger or hilt module
@InstallIn(SingletonComponent::class)//it means we want this to be created only once
class Appmodule {

    //now we are creating database
    @Singleton
    @Provides
    fun provideWeatherDao(weatherdatabase: WeatherDatabase):WeatherDao=weatherdatabase.weatherDao()


    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext context:Context):WeatherDatabase
    =Room.databaseBuilder(
        context = context,//application context is something that have access to everything in our project
        WeatherDatabase::class.java,
        "Weather_database") //it's name of database
        .fallbackToDestructiveMigrationFrom()
        .build()


    @Provides //dagger is about to provide
    @Singleton //we don't want repetation or instances of this class
    fun provideWeatherApi():WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Constants.Base_Url)
            .addConverterFactory(GsonConverterFactory.create()) //converting into objects
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGecodingApi():GecodingApi{
        return Retrofit.Builder()
            .baseUrl(Constants.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GecodingApi::class.java)
    }

}