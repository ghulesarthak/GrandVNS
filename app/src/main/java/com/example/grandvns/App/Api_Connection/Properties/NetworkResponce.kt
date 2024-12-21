package com.example.grandvns.App.Api_Connection.Properties


sealed class NetworkResponse<out T> {
        data class Sucess<out T>(val data:T):NetworkResponse<T>()
        data class Error<out T>(val massage:String):NetworkResponse<T>()
        object Loading:NetworkResponse<Nothing>()

    }
