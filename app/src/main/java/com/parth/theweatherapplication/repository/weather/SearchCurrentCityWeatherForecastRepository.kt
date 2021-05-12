package com.parth.theweatherapplication.repository.weather

import com.parth.theweatherapplication.api.OpenApi
import com.parth.theweatherapplication.persistence.CurrentWeatherDao
import com.parth.theweatherapplication.util.NetworkHandler
import javax.inject.Inject

class SearchCurrentCityWeatherForecastRepository
@Inject
constructor(
        val openApi: OpenApi,
        val currentWeatherDao: CurrentWeatherDao,
        val networkHandler: NetworkHandler
){

}