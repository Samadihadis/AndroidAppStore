package com.samadihadis.androidappstore.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("all")
    fun allApps(): Call<List<AppListModel>>
}