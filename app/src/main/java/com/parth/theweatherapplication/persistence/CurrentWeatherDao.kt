package com.parth.theweatherapplication.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.parth.theweatherapplication.model.SearchCurrentCityWeather
@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(searchCurrentCityWeatherEntity: SearchCurrentCityWeather)

    @Query("SELECT * FROM search_current_city_weather WHERE id = 1")
    fun searchById(): LiveData<SearchCurrentCityWeather>

    @Query("Select count(*) from search_current_city_weather")
    suspend fun getCount(): Int

    @Query("UPDATE search_current_city_weather SET name = :name, icon1 = :icon, temperature = :temperature, temp_min =:temp_min, temp_max=:temp_max WHERE id = :id")
    fun updateCurrentCity(id: Int, name: String, icon: String, temperature: String, temp_min: String, temp_max: String )

}