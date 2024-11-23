package com.example.weather.repository

import com.example.weather.data.WeatherDao
import com.example.weather.model.Favorites
import com.example.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherDtabseRepo @Inject constructor(private val WeatherDao:WeatherDao) { //wee need the DAo

    suspend fun insertFav(fav: Favorites) = WeatherDao.insertFavorite(fav)
    suspend fun updateFav(fav: Favorites) = WeatherDao.updateFavorite(fav)
    suspend fun deletefav(fav: Favorites) = WeatherDao.deleteFav(fav)
    suspend fun deleteall()=WeatherDao.deleteallfav()
    suspend fun getFavbyId(city:String):Favorites= WeatherDao.getFavbyId(city)

    //now to show all the currently stored favoriete items
    //use kotlin coroutines FLOW
    fun getAllFav():kotlinx.coroutines.flow.Flow<List<Favorites>> = WeatherDao.getFavorites().flowOn(Dispatchers.IO).conflate()

}