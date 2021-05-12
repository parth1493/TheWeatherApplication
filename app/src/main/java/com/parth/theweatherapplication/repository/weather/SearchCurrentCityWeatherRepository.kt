package com.parth.theweatherapplication.repository.weather


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.parth.theweatherapplication.api.OpenApi
import com.parth.theweatherapplication.api.weather.SearchCurrentCityWeatherResponse
import com.parth.theweatherapplication.model.SearchCurrentCityWeather
import com.parth.theweatherapplication.persistence.CurrentWeatherDao
import com.parth.theweatherapplication.repository.NetworkBoundResource
import com.parth.theweatherapplication.ui.DataState
import com.parth.theweatherapplication.ui.main.state.MainViewState
import com.parth.theweatherapplication.util.AbsentLiveData
import com.parth.theweatherapplication.util.ApiSuccessResponse
import com.parth.theweatherapplication.util.ErrorHandling.Companion.CITY_NOT_FOUND
import com.parth.theweatherapplication.util.ErrorHandling.Companion.CITY_NOT_FOUND_MESSAGE
import com.parth.theweatherapplication.util.ErrorHandling.Companion.INVALID_API
import com.parth.theweatherapplication.util.ErrorHandling.Companion.INVALID_API_MESSAGE
import com.parth.theweatherapplication.util.GenericApiResponse
import com.parth.theweatherapplication.util.NetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchCurrentCityWeatherRepository
@Inject
constructor(
    val openApi: OpenApi,
    val currentWeatherDao: CurrentWeatherDao,
    val networkHandler: NetworkHandler
)
{

    private val TAG: String = "AppDebug"

    private var repositoryJob: Job? = null


    fun getCurrentWeather(cityName: String ): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<SearchCurrentCityWeatherResponse, SearchCurrentCityWeather, MainViewState>(
            networkHandler.isNetworkAvailable(),
            true,
            true
        ){

            // if network is down, view the cache and return
            override suspend fun createCacheRequestAndReturn() {
                withContext(Dispatchers.Main){

                    // finishing by viewing db cache
                    result.addSource(loadFromCache()){ viewState ->
                        onCompleteJob(DataState.data(viewState, null))
                    }
                }
            }

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<SearchCurrentCityWeatherResponse>) {

                if(response.body.code.equals(CITY_NOT_FOUND)){
                    return onErrorReturn(CITY_NOT_FOUND_MESSAGE, true, false)
                }

                if(response.body.code.equals(INVALID_API)){
                    return onErrorReturn(INVALID_API_MESSAGE, true, false)
                }

                if(currentWeatherDao.getCount() == 1){
                updateLocalDb(
                    SearchCurrentCityWeather(1,
                                                response.body.name,
                                                response.body.weather?.get(0)!!.icon,
                                                response.body.main?.temp.toString(),
                                                response.body.main?.tempMin.toString(),
                                                response.body.main?.tempMax.toString())
                )
                }else{
                    currentWeatherDao.insertCurrentWeather(SearchCurrentCityWeather(1,
                                                response.body.name,
                                                response.body.weather?.get(0)!!.icon,
                                                response.body.main?.temp.toString(),
                                                response.body.main?.tempMin.toString(),
                                                response.body.main?.tempMax.toString()))
                }

                createCacheRequestAndReturn()
            }

            override fun loadFromCache(): LiveData<MainViewState> {
                return currentWeatherDao.searchById()
                    .switchMap {
                        object: LiveData<MainViewState>(){
                            override fun onActive() {
                                super.onActive()
                                value = MainViewState(it)
                            }
                        }
                    }
            }

            override suspend fun updateLocalDb(cacheObject: SearchCurrentCityWeather?) {
                Log.d("PARTH--5",cacheObject?.icon)
                cacheObject?.let {
                    currentWeatherDao.updateCurrentCity(
                        cacheObject.id,
                        cacheObject.name!!,
                        cacheObject.icon,
                        cacheObject.temperature!!,
                        cacheObject.temp_min!!,
                        cacheObject.temp_max!!
                    )
                }
            }

            override fun createCall(): LiveData<GenericApiResponse<SearchCurrentCityWeatherResponse>> {
                return openApi
                    .searchCity(
                        cityName
                    )
            }


            override fun setJob(job: Job) {
                repositoryJob?.cancel()
                repositoryJob = job
            }


        }.asLiveData()
    }

    fun getPreviousWeatherData(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<SearchCurrentCityWeatherResponse, SearchCurrentCityWeather, MainViewState>(
            networkHandler.isNetworkAvailable(),
            false,
            true
        ){

            // if network is down, view the cache and return
            override suspend fun createCacheRequestAndReturn() {
                withContext(Dispatchers.Main){

                    // finishing by viewing db cache
                    result.addSource(loadFromCache()){ viewState ->
                        onCompleteJob(DataState.data(viewState, null))
                    }
                }
            }

            // Ignore
            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<SearchCurrentCityWeatherResponse>) {


            }

            // Ignore
            override fun loadFromCache(): LiveData<MainViewState> {
                return currentWeatherDao.searchById()
                    .switchMap {
                        object: LiveData<MainViewState>(){
                            override fun onActive() {
                                super.onActive()
                                value = MainViewState(it)
                            }
                        }
                    }
            }

            // Ignore
            override suspend fun updateLocalDb(cacheObject: SearchCurrentCityWeather?) {

            }

            override fun createCall(): LiveData<GenericApiResponse<SearchCurrentCityWeatherResponse>> {
                return AbsentLiveData.create()
            }


            override fun setJob(job: Job) {
                repositoryJob?.cancel()
                repositoryJob = job
            }


        }.asLiveData()
    }

    fun cancelActiveJobs(){
        Log.d(TAG, "AuthRepository: Cancelling on-going jobs...")
        repositoryJob?.cancel()
    }
}