package com.cesoft.cestocks.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//2022-10-17 15:16:53.530 22333-22395/com.cesoft.cestocks I/okhttp.OkHttpClient:
// {"Error Message" : "Free plan is limited to US stocks only please visit our subscription page to upgrade your plan at https://financialmodelingprep.com/developer/docs/pricing"}
object NetworkDataSource {
    val apiService: ApiService
    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://financialmodelingprep.com/api/v3/")
            //.addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }
}