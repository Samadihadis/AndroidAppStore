package com.samadihadis.androidappstore.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AppAvailabilityModel(
    @SerializedName("available_in") val availableIn: List<String>?,
    @SerializedName("not_available_in") val notAvailableIn: List<String>?,
    @SerializedName("availability_unknown") val availabilityUnknown: List<String>?,
    @SerializedName("package_name") val packageName: String?
) : Serializable
