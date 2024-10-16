package com.example.weather.di

import com.example.weather.network.WeatherApi
import com.example.weather.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module //adding because it's dagger or hilt module
@InstallIn(SingletonComponent::class)//it means we want this to be created only once
class Appmodule {

    @Provides //dagger is about to provide
    @Singleton //we don't want repetation or instances of this class

    fun provideWeatherApi():WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Constants.Base_Url)
            .addConverterFactory(GsonConverterFactory.create()) //converting into objects
            .build()
            .create(WeatherApi::class.java)
    }


}