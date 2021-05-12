package com.parth.theweatherapplication.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.parth.theweatherapplication.model.SearchCurrentCityWeather

@Database(entities = [SearchCurrentCityWeather::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCurrentWeatherDao(): CurrentWeatherDao

    companion object{
        val DATABASE_NAME: String = "app_db"
    }
}