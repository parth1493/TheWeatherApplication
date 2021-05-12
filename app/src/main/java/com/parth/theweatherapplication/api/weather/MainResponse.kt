package com.parth.theweatherapplication.api.weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainResponse(

    @Expose
    @SerializedName("temp")
    val temp: Double?,

    @Expose
    @SerializedName("temp_min")
    var tempMin: Double?,

    @Expose
    @SerializedName("temp_max")
    var tempMax: Double?
)
