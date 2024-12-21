package com.example.grandvns.App.Navigation

import com.example.grandvns.App.Api_Connection.Agent.agentDataItem
import com.example.grandvns.App.App_UI.ForgotPass
import com.example.grandvns.App.App_UI.Registrion

object Route {
    var Login="Login"
    var Register="Register"
    var ForgotPass="ForgotPassword"
    var HomePage="HomePage"
    var LogOut="LogOut"
    var MyActivity="MyActivity"
    var MyInteraction="MyInteraction"
    var MyListingProject="MyListingProject"
    var PostPropeertySell="PostPropeertySell"
    var Profile="Profile"
    var Sample="Sample"
    var Splash="Splash"
    var Dashbord="Dashbord"
    var Single_Listing_Project="Single_Listing_Project/{propertyId}"
}















//
//
//
//
//package com.example.grandvns.App.App_UI
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Card
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.ExitToApp
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Refresh
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material.icons.filled.ThumbUp
//import androidx.compose.material.icons.rounded.Menu
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import coil.size.Size
//import com.example.grandvns.App.Api_Connection.Properties.AllPropertisItem
//import com.example.grandvns.App.App_UI.MenuBar.MyActivity
//import com.example.grandvns.App.App_UI.MenuBar.MyInteraction
//import com.example.grandvns.App.App_UI.MenuBar.MyListingProject
//import com.example.grandvns.App.App_UI.MenuBar.PostPropeertySell
//import com.example.grandvns.App.App_UI.MenuBar.Profile
//import com.example.grandvns.App.Navigation.Route
//import com.example.grandvns.App.Navigation.navigate
//import com.example.grandvns.R
//import com.example.grandvns.ui.theme.darkb
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomePage(paddingValues: PaddingValues,
//             propertisList: List<AllPropertisItem>,
//    //navController: NavController,
//) {
//
//
////    Column(
////        modifier = Modifier
////            .fillMaxSize()
////
////    ) {
////
////
////        Column(
////            modifier = Modifier
////                .verticalScroll(rememberScrollState())
////        ) {
////            Card(
////                modifier = Modifier
////                    .background(color = darkb)
////                    .fillMaxWidth()
////                    .padding(15.dp, 35.dp, 0.dp, 0.dp)
////            ) {
////                Text(
////                    text = "Grand VNS-Properties",
////                    color = Color.White,
////                    fontSize = 20.sp,
////                    modifier = Modifier
////                        .background(color = darkb)
////                        .fillMaxWidth()
////                        .size(40.dp)
////                        .padding(100.dp, 0.dp, 100.dp, 0.dp)
////                )
////            }
////            Spacer(modifier = Modifier.height(15.dp))
//
//    val coroutineScope = rememberCoroutineScope()
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val context = LocalContext.current.applicationContext
//
//
//
//
//
//    val navController = rememberNavController()
//
//    ModalNavigationDrawer(drawerState = drawerState,
//        gesturesEnabled = true,
//        drawerContent = {
//            ModalDrawerSheet {
//                Box(
//                    modifier = Modifier
//                        .background(color = darkb)
//                        .fillMaxWidth()
//                        .height(100.dp)
//
//
//                ) {
//                    Text(text = "****")
//                }
//                androidx.compose.material.Divider()
//
//                NavigationDrawerItem(label = {
//                    Text(
//                        text = "Profile",
//                        color = Color.Green
//                    )
//                },
//                    selected = false,
//                    icon = {
//                        Icon(
//                            imageVector = Icons.Default.Person,
//                            contentDescription = "Profile"
//                        )
//                    },
//                    onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController.navigate(Route.Profile) {
//                            popUpTo(0)
//                        }
//                    })
//
//                NavigationDrawerItem(label = {
//                    Text(
//                        text = "My Activity",
//                        color = Color.Green
//                    )
//                },
//                    selected = false,
//                    icon = {
//                        Icon(
//                            imageVector = Icons.Default.Refresh,
//                            contentDescription = "Profile"
//                        )
//                    },
//                    onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController.navigate(Route.MyActivity) {
//                            popUpTo(0)
//                        }
//                    })
//
//                NavigationDrawerItem(label = {
//                    Text(
//                        text = "My Interactions",
//                        color = Color.Green
//                    )
//                },
//                    selected = false,
//                    icon = {
//                        Icon(
//                            imageVector = Icons.Default.ThumbUp,
//                            contentDescription = "Profile"
//                        )
//                    },
//                    onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController.navigate(Route.MyInteraction) {
//                            popUpTo(0)
//                        }
//                    })
//
//                NavigationDrawerItem(label = {
//                    Text(
//                        text = "Post Propeerty Sell",
//                        color = Color.Green
//                    )
//                },
//                    selected = false,
//                    icon = {
//                        Icon(
//                            imageVector = Icons.Default.Home,
//                            contentDescription = "Profile"
//                        )
//                    },
//                    onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController
//                        navController.navigate(Route.PostPropeertySell) {
//                            popUpTo(0)
//                        }
//                    })
//
//                NavigationDrawerItem(label = {
//                    Text(
//                        text = "My Listing Project",
//                        color = Color.Green
//                    )
//                },
//                    selected = false,
//                    icon = {
//                        Icon(
//                            imageVector = Icons.Default.Star,
//                            contentDescription = "Profile"
//                        )
//                    },
//                    onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController.navigate(Route.MyListingProject) {
//                            popUpTo(0)
//                        }
//                    })
//
//                NavigationDrawerItem(label = {
//                    Text(
//                        text = "Log Out",
//                        color = Color.Green
//                    )
//                },
//                    selected = false,
//                    icon = {
//                        Icon(
//                            imageVector = Icons.Default.ExitToApp,
//                            contentDescription = "Profile"
//                        )
//                    },
//                    onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController.navigate(Route.Login) {
//                            popUpTo(0)
//                        }
//                    })
//            }
//        }) {
//        Scaffold(
//            topBar = {
//                val coroutineScope = rememberCoroutineScope()
//
//                TopAppBar(title = {
//
//                    Text(
//                        text = "Grand VNS-Properties",
//                        color = Color.White,
//                        fontSize = 20.sp,
//                        modifier = Modifier
//                            .background(color = darkb)
//                            .fillMaxWidth()
//                            .size(40.dp)
//                            .padding(70.dp, 0.dp, 80.dp, 0.dp)
//                    )
//                },
//                    colors = TopAppBarDefaults.topAppBarColors(
//                        containerColor = darkb,
//                        titleContentColor = Color.White
//                    ),
//
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            coroutineScope.launch { drawerState.open() }
//                        }) {
//                            Icon(Icons.Rounded.Menu, contentDescription = "menu", tint = Color.White, modifier = Modifier.size(30.dp))
//
//                        }
//                    }
//                )
//
//            }) {
//
//            NavHost(navController = navController, startDestination = Route.MyListingProject, builder = {
//
////                    composable(Route.Login) {
////                        flag = 1
////                        Login_Page(navController)
////                    }
//                composable(Route.Register) {
//                    Registrion(navController)
//                }
//                composable(Route.ForgotPass) {
//                    ForgotPass()
//
//                }
//                composable(Route.HomePage) {
//                    HomePage(paddingValues, propertisList)
//                }
//                composable(Route.Profile) {
//                    Profile()
//                }
//                composable(Route.MyActivity) {
//                    MyActivity()
//                }
//                composable(Route.MyInteraction) {
//                    MyInteraction()
//                }
//                composable(Route.PostPropeertySell) {
//                    PostPropeertySell()
//                }
//                composable(Route.MyListingProject) {
//
//                    MyListingProject(propertisList = propertisList, navController)
//                }
//                composable(Route.LogOut) {
//
//                    com.example.grandvns.App.App_UI.MenuBar.LogOut()
//                }
//
//            }
//            )
//
//        }
//
//    }
//}
//
//




