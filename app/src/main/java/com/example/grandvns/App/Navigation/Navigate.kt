package com.example.grandvns.App.Navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grandvns.App.App_UI.Login_Page
import com.example.grandvns.App.App_UI.ForgotPass
import com.example.grandvns.App.App_UI.HomePage
import com.example.grandvns.App.App_UI.Registrion

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.grandvns.App.Api_Connection.Properties.AllPropertisItem
import com.example.grandvns.App.Api_Connection.Agent.agentDataItem
import com.example.grandvns.App.App_UI.MainViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun navigate(
    paddingValues: PaddingValues,
    propertisList: List<AllPropertisItem>,
    AgentList: List<agentDataItem>,
    isLoggedIn: Boolean,
    isLoading: Boolean,
    viewModel: MainViewModel,


    ) {


    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = if (isLoggedIn) Route.HomePage else Route.Login, // Navigate based on login state
        builder = {

//        composable(Route.Splash){
//            Splash()
//        }

        composable(Route.Login) {

            Login_Page(navController, paddingValues, propertisList, AgentList)
        }
        composable(Route.Register) {

            Registrion(navController)
        }
        composable(Route.ForgotPass) {

            ForgotPass()

        }
        composable(Route.HomePage) {

            HomePage(paddingValues, propertisList, AgentList, navController,isLoading,viewModel)
        }


    }
    )

}












