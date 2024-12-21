package com.example.grandvns.App.Api_Connection.Agent

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//interface ApiInterface {
//
//    @GET("agent.php/")
//    fun getData(): Call<List<agentDataItem>>
//
//
//   // fun getData(): List<agentDataItem>
//
//}


interface AgentApiService {

    @GET("agent.php/")
    suspend fun getData(): List<agentDataItem>

    @POST("insertagent.php")
    fun PostData(@Body data: agentDataItem): Call<agentDataItem?>?




}



