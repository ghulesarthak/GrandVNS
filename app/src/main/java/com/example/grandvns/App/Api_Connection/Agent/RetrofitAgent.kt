package com.example.grandvns.App.Api_Connection.Agent


import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private val retrofit by lazy {
    Retrofit.Builder().baseUrl("https://gtsites.in/grandvns1/restapi/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
val apiInterfaceService by lazy {
    retrofit.create(AgentApiService::class.java)
}