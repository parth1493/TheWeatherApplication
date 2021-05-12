package com.parth.theweatherapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "search_current_city_weather")
class SearchCurrentCityWeather(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 1,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "icon1")
    val icon: String,

    @ColumnInfo(name = "temperature")
    val temperature: String? = null,

    @ColumnInfo(name = "temp_min")
    val temp_min: String? = null,

    @ColumnInfo(name = "temp_max")
    val temp_max: String? = null

    )
{

    override fun toString(): String {
        return "SearchCurrentCityWeather(id=$id, name=$name, icon='$icon', temperature=$temperature, temp_min=$temp_min, temp_max=$temp_max)"
    }
}