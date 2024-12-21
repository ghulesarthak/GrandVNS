package net.genericapps.imageuploader

import android.media.Image
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MyApi {

    @Multipart
   // @POST("Api.php?apicall=upload")
    @POST("uploadimgapi.php")
    abstract fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("desc") desc: RequestBody

    ): Call<imagegetaipItem>

    //abstract fun uploadImage(image: MultipartBody.Part): Call<imagegetaipItem>

    // abstract fun uploadImage(image: MultipartBody.Part): Call<imagegetaipItem>

    companion object {
        operator fun invoke(): MyApi{
            return Retrofit.Builder()
                .baseUrl("https://gtsites.in/grandvns1/restapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}


//https://gtsites.in/grandvns1/restapi/uploadimgapi.php
