package com.parth.theweatherapplication.api

import androidx.lifecycle.LiveData
import com.parth.theweatherapplication.BuildConfig
import com.parth.theweatherapplication.api.weather.SearchCurrentCityWeatherResponse
import com.parth.theweatherapplication.api.weather.SearchCurrentCityWeatherForecastResponse
import com.parth.theweatherapplication.util.Constants
import com.parth.theweatherapplication.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenApi {

    @GET("weather")
    fun searchCity(
        @Query("q") cityName:String,
        @Query("appid") apiKey:String = BuildConfig.WEATHER_KRY,
        @Query("units")units:String = Constants.UNITS
    ): LiveData<GenericApiResponse<SearchCurrentCityWeatherResponse>>

    @GET("weather")
    fun searchCityWeatherForecast(
            @Query("q") cityName:String,
            @Query("appid") apiKey:String = BuildConfig.WEATHER_KRY,
            @Query("units")units:String = Constants.UNITS
    ): LiveData<GenericApiResponse<SearchCurrentCityWeatherForecastResponse>>
}