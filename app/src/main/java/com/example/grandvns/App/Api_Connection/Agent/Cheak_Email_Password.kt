package com.example.grandvns.App.Api_Connection.Agent

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

import com.example.grandvns.App.Navigation.Route
import com.example.grandvns.App.User_Preferences.UserPreferences


fun Cheak_Email_Password(
    Email: String,
    Password: String,
    navController: NavController,
    context: Context,
    AgentList: List<agentDataItem>
){
    var flag = 0

   //User preference variable
    val userPreferences = UserPreferences(context)

    var AgentList=AgentList

        for ( data in AgentList )
        {
            if(Email==data.email && Password==data.password){
                flag=0
//after the user successfully logs in, update the login state and stoare userId  in SharedPreferences.
                userPreferences.setLoggedIn(true)
                userPreferences.setUserId(data.a_id!!)

               // val userPreferences = UserPreferences(context)
                userPreferences.setMobileNo(data.mobile!!)


                navController.navigate(Route.HomePage) {
                    popUpTo(Route.Login) { inclusive = true }
                }
              //  Agent= data
                break
            }
            if(Email!=data.email && Password!=data.password){
                flag=2
            }
           // data.fcenter

        }
        if(flag==2){
            Toast.makeText(context, "Invalid User_Name And Password", Toast.LENGTH_SHORT).show()
        }
}











