package com.parth.theweatherapplication.di.main.weather

import com.parth.theweatherapplication.api.OpenApi
import com.parth.theweatherapplication.persistence.CurrentWeatherDao
import com.parth.theweatherapplication.repository.weather.SearchCurrentCityWeatherForecastRepository
import com.parth.theweatherapplication.repository.weather.SearchCurrentCityWeatherRepository
import com.parth.theweatherapplication.util.Constants
import com.parth.theweatherapplication.util.NetworkHandler
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class WeatherModule {

    @Provides
    fun provideOpenApiAuthService(retrofitBuilder: Retrofit.Builder): OpenApi {
        return retrofitBuilder
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(OpenApi::class.java)
    }

    @Provides
    fun provideSearchCurrentCityWeatherRepository(
        openApi: OpenApi,
        currentWeatherDao: CurrentWeatherDao,
        networkHandler: NetworkHandler
    ): SearchCurrentCityWeatherRepository {
        return SearchCurrentCityWeatherRepository(
            openApi,
            currentWeatherDao,
            networkHandler
        )
    }

    @Provides
    fun provideSearchCurrentCityWeatherForecastRepository(
            openApi: OpenApi,
            currentWeatherDao: CurrentWeatherDao,
            networkHandler: NetworkHandler
    ): SearchCurrentCityWeatherForecastRepository {
        return SearchCurrentCityWeatherForecastRepository(
                openApi,
                currentWeatherDao,
                networkHandler
        )
    }
}