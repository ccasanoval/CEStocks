package com.cesoft.cestocks.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://financialmodelingprep.com/api/v3/")
        //.addConverterFactory(MoshiConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}