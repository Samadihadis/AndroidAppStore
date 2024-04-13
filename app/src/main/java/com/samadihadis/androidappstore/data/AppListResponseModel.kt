package com.samadihadis.androidappstore.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AppListResponseModel(

    @SerializedName("list_name") val listName: String,
    @SerializedName("cat_key") val catKey: String,
    @SerializedName("category_name") val categoryName: String,
    @SerializedName("country") val country: String,
    @SerializedName("date") val date: String,
    @SerializedName("app_list") val appList: ArrayList<AppAvailabilityModel>,
    @SerializedName("limit") val limit: Int,
    @SerializedName("number_results") val numberResult: Int,
    @SerializedName("has_next") val hasNext: Boolean,
    @SerializedName("page") val page: Int,
    @SerializedName("num_pages") val numPage: Int

) : Serializable
