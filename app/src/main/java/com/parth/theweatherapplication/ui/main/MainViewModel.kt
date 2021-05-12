package com.parth.theweatherapplication.ui.main


import androidx.lifecycle.LiveData
import com.parth.theweatherapplication.model.SearchCurrentCityWeather
import com.parth.theweatherapplication.repository.weather.SearchCurrentCityWeatherRepository
import com.parth.theweatherapplication.ui.BaseViewModel
import com.parth.theweatherapplication.ui.DataState
import com.parth.theweatherapplication.ui.main.state.MainStateEvent
import com.parth.theweatherapplication.ui.main.state.MainViewState
import com.parth.theweatherapplication.util.AbsentLiveData
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val searchCurrentCityWeatherRepository: SearchCurrentCityWeatherRepository
): BaseViewModel<MainStateEvent, MainViewState>() {

    override fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        when(stateEvent){

            is MainStateEvent.SearchCurrentCityEvent ->{
                return searchCurrentCityWeatherRepository.getCurrentWeather(
                    stateEvent.cityName
                )
            }

            is MainStateEvent.None ->{
                return AbsentLiveData.create()
            }

            is MainStateEvent.CheckPreviousWeatherData ->{
                return searchCurrentCityWeatherRepository.getPreviousWeatherData()
            }
        }
    }

    override fun initNewViewState(): MainViewState {
        return MainViewState()
    }

    fun setSearchCurrentCityWeather(searchCurrentCityWeather: SearchCurrentCityWeather){
        val update = getCurrentViewStateOrNew()
        if(update.searchCurrentCityWeather == searchCurrentCityWeather){
            return
        }
        update.searchCurrentCityWeather = searchCurrentCityWeather
        _viewState.value = update
    }
}
