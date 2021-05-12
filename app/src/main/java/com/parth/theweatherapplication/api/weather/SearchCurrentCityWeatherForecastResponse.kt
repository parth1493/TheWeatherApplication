package com.parth.theweatherapplication.api.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchCurrentCityWeatherForecastResponse(
        @Expose
        @SerializedName("cod")
        var code: String? = null,

        @Expose
        @SerializedName("message")
        var error_message: String? = null,

        @Expose
        @SerializedName("list")
        val weather: List<ListItemResponse?>? = null

)
