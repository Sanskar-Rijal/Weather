package com.example.weather.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weather.model.Favorites
import com.example.weather.model.Unit
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {
    @Query("SELECT * from list_of_favorites") ////* means all , i mean select all elements form notes tbl

    fun getFavorites(): Flow<List<Favorites>> //we want to recieve all of them , so we will make a list
    //compose it response to changes of our data so wrapping it in flow


    @Query("SELECT * from list_of_favorites where city =:city")
    suspend fun getFavbyId(city:String) :Favorites //we are using city:String up finding it from list of favorites

    //if we want to insert something
    @Insert(onConflict = OnConflictStrategy.REPLACE) //if there is some conflict just go ahead and replace that
    suspend fun insertFavorite(favorites: Favorites)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorites: Favorites)

    //delete everything
    @Query("DELETE from list_of_favorites")
    suspend fun deleteallfav()

    //delete selected item
    @Delete
    suspend fun deleteFav(favorites: Favorites)


    //for UNIT TABLE
    @Query("SELECT * from  Settings_tbl")
    fun getunit(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit:Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query("DELETE from settings_tbl")
    suspend fun deleteallUnit()

    //delete selected item
    @Delete
    suspend fun deleteUnit(unit:Unit)

}