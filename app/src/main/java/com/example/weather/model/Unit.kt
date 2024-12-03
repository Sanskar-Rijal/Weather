package com.example.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

//sql database
@Entity(tableName = "Settings_tbl")
data class Unit(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name ="unit")
    val unit:String
)
