package com.parth.theweatherapplication.util

class Constants {

    companion object{

        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val IMAGE_URL = "https://openweathermap.org/img/w/"
        const val NETWORK_TIMEOUT = 3000L
        const val TESTING_NETWORK_DELAY = 0L // fake network delay for testing
        const val TESTING_CACHE_DELAY = 0L // fake cache delay for testing
        const val UNITS = "metric"
    }
}