package com.example.weather.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull


//this is the blueprint for our database, which indicates the thing that it will contain

@Entity(tableName = "list_of_favorites") //Entity means that this is a table in our database with columns
data class Favorites(
    //every table must have a sort of primary key (unique key) so that it can access the data

    @Nonnull
    @PrimaryKey// making city key as primary key
    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name="country")
    val country:String
)
