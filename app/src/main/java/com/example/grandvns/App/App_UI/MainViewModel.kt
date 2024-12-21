package com.example.grandvns.App.App_UI


import android.content.Context
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.grandvns.App.Api_Connection.Properties.AllPropertisItem
import com.example.grandvns.App.Api_Connection.Properties.RetrofitInstanceProperties

import com.example.grandvns.App.Api_Connection.Agent.agentDataItem
import com.example.grandvns.App.Api_Connection.Agent.apiInterfaceService
import com.example.grandvns.App.Api_Connection.Properties.AllPropertis
import com.example.grandvns.App.Api_Connection.Properties.NetworkResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainViewModel : ViewModel() {
    var PrortisLIstResponce: List<AllPropertisItem> by mutableStateOf(listOf())
    var errormessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false) // This will track if data is loading
   //
    //for network responce
    private val _ApiResult = MutableLiveData<NetworkResponse<AllPropertis>>()
    val Result: LiveData<NetworkResponse<AllPropertis>> = _ApiResult

    fun getPropertiList() {

        _ApiResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {

                val response = RetrofitInstanceProperties.api.getItems()
                //  PrortisLIstResponce=response
                if (response.isSuccessful) {
                    response.body()?.let {
                        _ApiResult.value = NetworkResponse.Sucess<AllPropertis>(it)
                    }
                } else {
                    _ApiResult.value = NetworkResponse.Error(response.message())
                }
            } catch (e: Exception) {
                _ApiResult.value = NetworkResponse.Error("Fail to Load Data")
            } finally {
                isLoading = false  // Stop loading when data is fetched or error occurs
            }

        }
    }







    var isLoadingp by mutableStateOf(false) // Loading state
fun postPropertiList(
    photoPart: MultipartBody.Part,
    videoPart: MultipartBody.Part?,
    formData: MutableMap<String, RequestBody>,
    context: Context
) {

    isLoadingp = true // Start loading

// Make the API call
val apiCall = RetrofitInstanceProperties.api.Post_P_Data(photoPart, videoPart, formData)

if (apiCall != null) {
    apiCall.enqueue(object : Callback<AllPropertisItem?> {
        @OptIn(UnstableApi::class)
        override fun onResponse(call: Call<AllPropertisItem?>, response: Response<AllPropertisItem?>) {
            if (response.isSuccessful) {
                // Handle successful API response
                Toast.makeText(context, "Data posted successfully", Toast.LENGTH_SHORT).show()
                Log.d("API_SUCCESS", "Response: ${response.body()}")
                isLoadingp = false // Stop loading
            } else {

                // Handle error response
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Toast.makeText(context, "Failed to post data: $errorBody", Toast.LENGTH_SHORT).show()
                Log.e("API_ERROR", "Error: $errorBody")
                isLoadingp = false // Stop loading
            }
        }

        @OptIn(UnstableApi::class)
        override fun onFailure(call: Call<AllPropertisItem?>, t: Throwable) {

            // Handle failure to communicate with API
            val errorMsg = t.message ?: "Unknown network error"
            Toast.makeText(context, "Network failure: $errorMsg", Toast.LENGTH_SHORT).show()
            Log.e("API_FAILURE", "Failure: $errorMsg", t)
            isLoadingp = false // Stop loading on failure
        }
    })
}
}
}


class AgentProfileViewModel:ViewModel(){
    var AgentListResponsce:List<agentDataItem> by mutableStateOf(listOf())

    fun getAgentDataList(){
        viewModelScope.launch {
            try {
                val AgentList= apiInterfaceService.getData()//RtrofitAgent.api.getData()
                AgentListResponsce=AgentList
            }
            catch (e:Exception){

            }
        }
    }
}




//
//fun postPropertiList(
//    photoPart: MultipartBody.Part,
//    videoPart: MultipartBody.Part?,
//    formData: MutableMap<String, RequestBody>,
//    context: Context
//) {

//// Make the API call
//val apiCall = RetrofitInstanceProperties.api.Post_P_Data(photoPart, videoPart, formData)
//
//if (apiCall != null) {
//    apiCall.enqueue(object : Callback<AllPropertisItem?> {
//        @OptIn(UnstableApi::class)
//        override fun onResponse(call: Call<AllPropertisItem?>, response: Response<AllPropertisItem?>) {
//            if (response.isSuccessful) {
//                // Handle successful API response
//                Toast.makeText(context, "Data posted successfully", Toast.LENGTH_SHORT).show()
//                Log.d("API_SUCCESS", "Response: ${response.body()}")
//            } else {
//                // Handle error response
//                val errorBody = response.errorBody()?.string() ?: "Unknown error"
//                Toast.makeText(context, "Failed to post data: $errorBody", Toast.LENGTH_SHORT).show()
//                Log.e("API_ERROR", "Error: $errorBody")
//            }
//        }
//
//        @OptIn(UnstableApi::class)
//        override fun onFailure(call: Call<AllPropertisItem?>, t: Throwable) {
//            // Handle failure to communicate with API
//            val errorMsg = t.message ?: "Unknown network error"
//            Toast.makeText(context, "Network failure: $errorMsg", Toast.LENGTH_SHORT).show()
//            Log.e("API_FAILURE", "Failure: $errorMsg", t)
//        }
//    })
//}
//}