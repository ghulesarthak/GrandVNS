package com.example.grandvns.App.Api_Connection.Properties


import okhttp3.OkHttpClient
import okhttp3.internal.platform.android.AndroidLogHandler.setLevel
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



object RetrofitInstanceProperties {

    // Create a logging interceptor
    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY) // Logs request and response body
    }

    // Create an OkHttpClient and add the interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging) // Add the logging interceptor
        .connectTimeout(30, TimeUnit.SECONDS) // Optional: set timeouts
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://gtsites.in/grandvns1/restapi/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}


