package com.parth.theweatherapplication.api.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherItemResponse(

    @Expose
    @SerializedName("icon")
    val icon: String,

    @Expose
    @SerializedName("id")
    val id: Int?
)
