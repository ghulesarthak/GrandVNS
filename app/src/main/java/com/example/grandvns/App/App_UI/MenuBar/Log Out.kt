package com.example.grandvns.App.App_UI.MenuBar

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.grandvns.App.Api_Connection.Properties.AllPropertisItem
import com.example.grandvns.App.Api_Connection.Agent.agentDataItem
import com.example.grandvns.App.Navigation.Route
import com.example.grandvns.App.Navigation.navigate
import com.example.grandvns.App.User_Preferences.UserPreferences

fun logout(navController: NavController, context: Context) {
    val userPreferences = UserPreferences(context)
    userPreferences.clearLoginState()

    // Navigate to login page
    navController.navigate(Route.Login) {
        popUpTo(Route.HomePage) { inclusive = true }
    }
}
