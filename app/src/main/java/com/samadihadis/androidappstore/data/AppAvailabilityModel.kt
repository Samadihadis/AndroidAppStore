package com.samadihadis.androidappstore.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AppAvailabilityModel(

    @SerializedName("available_in") val availableIn: ArrayList<String>,
    @SerializedName("not_available_in") val notAvailableIn: ArrayList<String>,
    @SerializedName("availability_unknown") val availabilityUnknown: ArrayList<String>,
    @SerializedName("package_name") val packageName: String

) : Serializable
