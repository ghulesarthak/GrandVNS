package com.example.grandvns.App.Api_Connection.Properties

import net.genericapps.imageuploader.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
//we are created interface  for CRUD operation
interface ApiService {


    @GET("allproperty.php")
    suspend fun getItems(): Response<AllPropertis>//List<AllPropertisItem>




    @Multipart
    @POST("insertpostpropery.php")
    fun Post_P_Data(
        @Part photo: MultipartBody.Part,  // For the image
        @Part video: MultipartBody.Part?,  // For the video (nullable if not always present)
        @PartMap data: Map<String, @JvmSuppressWildcards RequestBody> // For the other fields
    ): Call<AllPropertisItem>


    //https://gtsites.in/grandvns1/restapi/insertpostpropery.php

    /*
    @Multipart
    @POST("insertpostpropery.php")
    fun uploadProperty(
        @Part("propertyid") propertyId: RequestBody,
        @Part("buildtype") buildType: RequestBody,
        @Part("proptype") propType: RequestBody,
        @Part("city") city: RequestBody,
        @Part("projectname") projectName: RequestBody,
        @Part("locality") locality: RequestBody,
        @Part("price") price: RequestBody,
        @Part("no_of_rooms") noOfRooms: RequestBody,
        @Part("area") area: RequestBody,
        @Part("area_type") areaType: RequestBody,
        @Part("possession_status") possessionStatus: RequestBody,
        @Part("furnishing_status") furnishingStatus: RequestBody,
        @Part("ageofprop") ageOfProp: RequestBody,
        @Part("no_of_baths") noOfBaths: RequestBody,
        @Part("coveredparking") coveredParking: RequestBody,
        @Part("openparking") openParking: RequestBody,
        @Part("balcony") balcony: RequestBody,
        @Part("powerbackup") powerBackup: RequestBody,
        @Part("facing") facing: RequestBody,
        @Part("selectflooring") selectFlooring: RequestBody,
        @Part("floornumber") floorNumber: RequestBody,
        @Part("tower") tower: RequestBody,
        @Part("totalfloor") totalFloor: RequestBody,
        @Part("unitno") unitNo: RequestBody,
        @Part("amenities") amenities: RequestBody,
        @Part("defininglocation") definingLocation: RequestBody,
        @Part("explainingprice") explainingPrice: RequestBody,
        @Part("explainingtheproperty") explainingTheProperty: RequestBody,
        @Part("definesize") defineSize: RequestBody,
        @Part("suitablefor") suitableFor: RequestBody,
        @Part("description") description: RequestBody,
        @Part("status") status: RequestBody,
        @Part("createdby") createdBy: RequestBody,
        @Part("createdon") createdOn: RequestBody,

        // Handle image and video upload
        @Part photo: List<MultipartBody.Part>,
        @Part video: MultipartBody.Part?
    ): Call<UploadResponse>*/
}
