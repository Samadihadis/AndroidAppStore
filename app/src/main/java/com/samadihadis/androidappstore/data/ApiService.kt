package com.samadihadis.androidappstore.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v3.0/android/apps/top_google_charts.json")
    fun topGoogleAppCharts(
        @Query("list_name") listName: String= "topselling_free",
        @Query("cat_key") catKey: String,
        @Query("country") country: String = "US",
        @Query("limit") limit: String = "10",
        @Query("access_token") accessToken: String = "43bde3f4e42466435aab1b279ac81061a1abd076",
    ): Call<AppListResponseModel>

}