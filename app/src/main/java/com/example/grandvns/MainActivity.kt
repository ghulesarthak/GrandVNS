package com.example.grandvns

//import com.example.grandvns.App.App_UI.Splash
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.grandvns.App.App_UI.AgentProfileViewModel
import com.example.grandvns.App.App_UI.MainViewModel
import com.example.grandvns.App.Navigation.navigate
import com.example.grandvns.App.User_Preferences.UserPreferences
import com.example.grandvns.ui.theme.GrandvnsTheme


class MainActivity : ComponentActivity() {

     val viewModel by viewModels<MainViewModel>()
     val AgentViewModel by viewModels<AgentProfileViewModel>()

                    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                    override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)
                        enableEdgeToEdge()

                        // Initialize UserPreferences
                        val userPreferences = UserPreferences(applicationContext)

                        setContent {
                            GrandvnsTheme {
                                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                                    // Check if the user is logged in
                                    val isLoggedIn = userPreferences.isLoggedIn()

                                    // loading state check
                                   val isLoading = viewModel.isLoading  // Pass loading state to composable

                                    // Navigate based on login state
                                    navigate(
                                        innerPadding,
                                        propertisList = viewModel.PrortisLIstResponce,
                                        AgentList = AgentViewModel.AgentListResponsce,
                                        isLoggedIn = isLoggedIn,
                                        isLoading,viewModel
                                    )

                    // Fetch data
                    viewModel.getPropertiList()
                    AgentViewModel.getAgentDataList()
                }
            }
        }
    }
}
