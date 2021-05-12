package com.parth.theweatherapplication.ui.main.state


sealed class MainStateEvent{


   data class SearchCurrentCityEvent(
       val cityName:String
   ):MainStateEvent()

    class None: MainStateEvent()

    class CheckPreviousWeatherData(): MainStateEvent()
}




