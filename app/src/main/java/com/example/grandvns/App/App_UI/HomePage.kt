package com.example.grandvns.App.App_UI

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grandvns.App.Api_Connection.Agent.agentDataItem
import com.example.grandvns.App.Api_Connection.Properties.AllPropertisItem
import com.example.grandvns.App.Api_Connection.Properties.NetworkResponse

import com.example.grandvns.App.App_UI.MenuBar.Dashbord
import com.example.grandvns.App.App_UI.MenuBar.MyActivity
import com.example.grandvns.App.App_UI.MenuBar.MyInteraction
import com.example.grandvns.App.App_UI.MenuBar.MyListingProject
import com.example.grandvns.App.App_UI.MenuBar.PostPropeertySell
import com.example.grandvns.App.App_UI.MenuBar.Profile
import com.example.grandvns.App.App_UI.MenuBar.Single_Listing_Project

import com.example.grandvns.App.App_UI.MenuBar.logout
import com.example.grandvns.App.Navigation.Route

import com.example.grandvns.App.User_Preferences.UserPreferences
import com.example.grandvns.R
import com.example.grandvns.ui.theme.darkb
import com.example.grandvns.ui.theme.darkb1
import kotlinx.coroutines.launch
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun HomePage(
    paddingValues: PaddingValues,
    propertisList: List<AllPropertisItem>,
    AgentList: List<agentDataItem>,
    navController2: NavController,
    isLoading: Boolean,
    viewModel: MainViewModel,

    ) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        darkb1, Color.White
                    )
                )
            )
    ) {


        val coroutineScope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        LocalContext.current.applicationContext


        val navController1 = rememberNavController()
        val context = LocalContext.current

        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = true,
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier.padding(top = 80.dp)) {
                    Box(
                        modifier = Modifier
                            .background(darkb)
                            .fillMaxWidth()
                            .height(60.dp),
                        contentAlignment = Alignment.CenterStart
                    )
                    {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile Icon",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp).padding(8.dp)
                            )


                            val AgentList = AgentList

                            //User preference variable
                            val userPreferences = UserPreferences(context)
                            val userId = userPreferences.getUserId()


                            for (data in AgentList) {

                                if (userId == data.a_id) {

                                    Text(
                                        text = data.fullname!!,
                                        modifier = Modifier.padding(top=8.dp),
                                        color = Color.White,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    //for loop break
                                    break
                                }
                            }
                        }
                    }
                    Divider()
                    NavigationDrawerItem(label = {
                        Text(
                            text = "Profile",
                            color = darkb
                        )
                    },
                        selected = false,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile"
                            )
                        },
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            navController1.navigate(Route.Profile) {
                               // popUpTo(0)
                                   popUpTo(navController1.graph.startDestinationId)
                                    launchSingleTop=true
                            }
                        })


                    NavigationDrawerItem(label = {
                        Text(
                            text = "My Listing Project",
                            color = darkb
                        )
                    },
                        selected = false,


//                    val iconImage: Painter = painterResource(id = R.drawable.phonecall)
//
//                Image(
//                    painter = iconImage,
//                    contentDescription = "WhatsApp Icon",
//                    modifier = Modifier.size(24.dp),
//                    // colorFilter = ColorFilter.tint(Color.White) // Optional tinting
//                )

                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.propertylist1),
                                contentDescription = "Listing",
                                modifier = Modifier.size(24.dp),
                            )
                        },
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            navController1.navigate(Route.MyListingProject) {
                               // popUpTo(0)
                                   popUpTo(navController1.graph.startDestinationId)
                                    launchSingleTop=true
                            }
                        })

                    NavigationDrawerItem(label = {
                        Text(
                            text = "Post Propeerty Sell",
                            color = darkb
                        )
                    },
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_maps_home_work_24),
                                contentDescription = "Sell"
                            )
                        },
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                           // navController1
                            navController1.navigate(Route.PostPropeertySell) {
                                   popUpTo(navController1.graph.startDestinationId)
                                    launchSingleTop=true
                            }
                        })

                    NavigationDrawerItem(label = {
                        Text(
                            text = "My Interactions",
                            color = darkb
                        )
                    },
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_apartment_24),
                                contentDescription = "My Interactions"
                            )
                        },
                        onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController1.navigate(Route.MyInteraction) {
//                            popUpTo(0)
//                        }
                        })

                    NavigationDrawerItem(label = {
                        Text(
                            text = "My Income",
                            color = darkb
                        )
                    },
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_currency_rupee_24),
                                contentDescription = "My Income"
                            )
                        },
                        onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController1.navigate(Route.MyActivity) {
//                            popUpTo(0)
//                        }
                        })
                    //show dilog box variable
                    val showDialog = remember { mutableStateOf(false) }

                    NavigationDrawerItem(label = {
                        Text(
                            text = "LogOut",
                            color = darkb
                        )
                    },
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_logout_24),
                                contentDescription = "LogOut"
                            )
                        },
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            //Show dilog Box
                            showDialog.value = true
                        })


                    // Place the LogoutDialog outside of the IconButton onClick handler
                    LogoutDialog(
                        navController = navController2,
                        context = context,
                        showDialog = showDialog.value,
                        onDismiss = {
                            showDialog.value = false
                        } // Close the dialog when dismissed
                    )
                }

            }
        ) {

            val selected = remember {
                mutableStateOf(Icons.Default.Home)
            }

            Scaffold(
                topBar = {
                    val coroutineScope = rememberCoroutineScope()

                    TopAppBar(
                        title = {

                            Text(
                                text = "Grand VNS-Properties",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier
                                    .background(color = darkb)
                                    .fillMaxWidth()
                                    .size(30.dp)

                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = darkb,
                            titleContentColor = Color.White
                        ),

                        navigationIcon = {

                            IconButton(onClick = {
                                coroutineScope.launch { drawerState.open() }
                            }) {
                                Icon(
                                    Icons.Rounded.Menu,
                                    contentDescription = "menu",
                                    tint = Color.White,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        },


                        )
                },




                    bottomBar = {
                        // Show dialog box variable
                        val showDialog = remember { mutableStateOf(false) }

                        // Place the LogoutDialog outside of the IconButton onClick handler
                        AppClosed(
                            navController = navController2,
                            context = context,
                            showDialog = showDialog.value,
                            onDismiss = {
                                showDialog.value = false
                            } // Close the dialog when dismissed
                        )


                        BottomAppBar(
                            containerColor = darkb,
                            modifier = Modifier.height(70.dp) // Set the height of the BottomAppBar
                        ) {
                            IconButton(
                                onClick = {
                                    selected.value = Icons.Default.Home
                                    navController1.navigate(Route.HomePage) {
                                        popUpTo(0)
                                    }
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    Icons.Default.Home,
                                    contentDescription = null,
                                    modifier = if (selected.value == Icons.Default.Home) Modifier.size(35.dp) else Modifier.size(26.dp),
                                    tint = if (selected.value == Icons.Default.Home) Color.White else Color.Gray
                                )
                            }

                            // FloatingActionButton in the center of the BottomAppBar
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                   // .padding(3.dp)
                                ,contentAlignment = Alignment.Center
                            ) {
                                FloatingActionButton(
                                    onClick = {
                                        selected.value = Icons.Default.Add
                                        navController1.navigate(Route.PostPropeertySell) {
                                            popUpTo(navController1.graph.startDestinationId)
                                            launchSingleTop = true
                                        }
                                    },
                                    modifier = Modifier
                                        .size(if (selected.value == Icons.Default.Add) 55.dp else 45.dp), // Dynamic size based on condition
                                    containerColor = if (selected.value == Icons.Default.Add) Color.White else darkb1
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = null, tint = if (selected.value == Icons.Default.Add) darkb else Color.White)
                                }
                            }

                            IconButton(
                                onClick = {
                                    selected.value = Icons.Default.Notifications
                                    navController1.navigate(Route.MyListingProject) {
                                        popUpTo(navController1.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.propertylist1),
                                    contentDescription = null,
                                    modifier = if (selected.value == Icons.Default.Notifications) Modifier.size(35.dp) else Modifier.size(26.dp),
                                    tint = if (selected.value == Icons.Default.Notifications) Color.White else Color.Gray
                                )
                            }
                        }


                }

            ) {


                NavHost(
                    navController = navController1,
                    startDestination = Route.Dashbord,
                    builder = {

                        composable(Route.Register) {
                            Registrion(navController1)
                        }
                        composable(Route.ForgotPass) {
                            ForgotPass()

                        }
                        composable(Route.HomePage) {
                            HomePage(
                                paddingValues,
                                propertisList,
                                AgentList,
                                navController2,
                                isLoading,
                                viewModel,
                            )
                        }
                        composable(Route.Profile) {
                            Profile(AgentList, context)
                        }
                        composable(Route.MyActivity) {
                            MyActivity()
                        }
                        composable(Route.MyInteraction) {
                            MyInteraction()
                        }
                        composable(Route.PostPropeertySell) {
                            PostPropeertySell(navController1, context, viewModel)
                        }
                        composable(Route.MyListingProject) {

                            MyListingProject(
                                navController1,
                                viewModel
                            )
                        }
                        composable(Route.Dashbord)
                        {
                            Dashbord(propertisList, navController1, isLoading, viewModel,paddingValues)
                        }
                        composable(Route.Single_Listing_Project)
                        { backStackEntry ->

                            // Get the propertyId from the route argument
                            val propertyId = backStackEntry.arguments?.getString("propertyId")

                            val result = viewModel.Result.observeAsState().value
                            when (result) {
                                is NetworkResponse.Error -> {
                                    Log.i("RN", result.massage)
                                    Toast.makeText(context, "CHEK THE DATA", Toast.LENGTH_SHORT)
                                        .show()
                                    Text(text = result.massage)
                                }

                                NetworkResponse.Loading -> {
                                    //CircularProgressIndicator()
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.fillMaxSize()
                                    ) {

                                        CircularProgressIndicator()
                                    }
                                }

                                is NetworkResponse.Sucess -> {

                                    val property = result.data.find { it.propertyid == propertyId }

                                    if (property != null) {
                                        Single_Listing_Project(property,AgentList)
                                    }
                                    // PropertyList(propertisList,navController,isLoading)

                                }

                                null -> TODO()
                            }


                        }
                    }
                )
            }
        }
    }
}




//LogOut DialogBox
@Composable
fun LogoutDialog(
    navController: NavController,
    context: Context,
    showDialog: Boolean,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Logout Confirmation") },
            text = { Text("Are you sure you want to log out?") },
            confirmButton = {
                Button(onClick = {
                    // Perform logout action
                    logout(navController, context)
                    onDismiss() // Close the dialog
                },  colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red, // Background color for dismiss button
                    contentColor = Color.White   // Text color for dismiss button
                )) {
                    Text("Logout")
                }
            },
            dismissButton = {
                Button(onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                        containerColor = darkb, // Background color for dismiss button
                    contentColor = Color.White   // Text color for dismiss button
                )
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}



//LogOut DialogBox
@Composable
fun AppClosed(
    navController: NavController,
    context: Context,
    showDialog: Boolean,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Stop_App Confirmation") },
            text = { Text("Are you sure you want to Stop_App?") },
            confirmButton = {
                Button(onClick = {
                    // Perform logout action
                   // logout(navController, context)
                    onDismiss() // Close the dialog
                },  colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red, // Background color for dismiss button
                    contentColor = Color.White   // Text color for dismiss button
                )) {
                    Text("Stop_App")
                }
            },
            dismissButton = {
                Button(onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkb, // Background color for dismiss button
                        contentColor = Color.White   // Text color for dismiss button
                    )
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

