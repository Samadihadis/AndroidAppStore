package com.samadihadis.androidappstore.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v3.0/android/apps/top_google_charts.json")
    fun topGoogleAppCharts(
       // @Query("list_name") listName: String,
       // @Query("cat_key") catKey: String,
        //@Query("country") country: String,
        //@Query("limit") limit: String,
        //@Query("access_token") accessToken: String,
    ): Call<AppListResponseModel>

}