package com.example.grandvns.App.User_Preferences


import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "user_prefs"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_MOBILE_NO = "mobile_no" // Added key for mobile number
    }

    // Function to save login state
    fun setLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    // Function to save user ID
    fun setUserId(userId: String) {
        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply()
    }

    // Function to get login state
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    // Function to get user ID
    fun getUserId(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }
    // Function to save mobile number
    fun setMobileNo(mobileNo: String) {
        sharedPreferences.edit().putString(KEY_MOBILE_NO, mobileNo).apply()
    }

    // Function to get mobile number
    fun getMobileNo(): String? {
        return sharedPreferences.getString(KEY_MOBILE_NO, null)
    }

    // Function to clear login state and user ID
    fun clearLoginState() {
        sharedPreferences.edit().clear().apply()
    }
}
