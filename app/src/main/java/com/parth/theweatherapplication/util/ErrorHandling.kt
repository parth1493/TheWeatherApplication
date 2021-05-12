package com.parth.theweatherapplication.util

class ErrorHandling {

    companion object{

        const val UNABLE_TO_RESOLVE_HOST = "Unable to resolve host"
        const val UNABLE_TODO_OPERATION_WO_INTERNET = "Can't do that operation without an internet connection"
        const val ERROR_CHECK_NETWORK_CONNECTION = "Check network connection."
        const val CITY_NOT_FOUND = "404"
        const val CITY_NOT_FOUND_MESSAGE = "City not found or invalid city name"
        const val INVALID_API = "401"
        const val INVALID_API_MESSAGE = "Invalid API key"
        const val ERROR_SAVE_AUTH_TOKEN = "Error saving authentication token.\nTry restarting the app."

        const val ERROR_UNKNOWN = "Unknown error"

        fun isNetworkError(msg: String): Boolean{
            when{
                msg.contains(UNABLE_TO_RESOLVE_HOST) -> return true
                else-> return false
            }
        }
    }
}