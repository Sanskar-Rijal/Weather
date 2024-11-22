package com.example.weather.data

import androidx.room.Dao
import androidx.room.Query
import com.example.weather.model.Favorites
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {
    @Query("SELECT * from list_of_favorites") ////* means all , i mean select all elements form notes tbl

    fun getFavorites(): Flow<List<Favorites>> //we want to recieve all of them , so we will make a list
    //compose it response to changes of our data so wrapping it in flow
}