package com.parth.theweatherapplication.api.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListItemResponse(

        @Expose
        @SerializedName("weather")
        val weather: List<WeatherItemResponse?>? = null,

        @Expose
        @SerializedName("main")
        val main: MainResponse? = null,

        @Expose
        @SerializedName("dt_txt")
        val dt_txt: String? = null
)
