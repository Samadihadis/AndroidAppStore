package com.samadihadis.androidappstore.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PermissionsModel(

    @SerializedName("id") val id: String,
    @SerializedName("source") val source: String

) : Serializable
