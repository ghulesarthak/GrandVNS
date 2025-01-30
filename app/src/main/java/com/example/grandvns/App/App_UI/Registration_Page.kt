package com.example.grandvns.App.App_UI
//import android.widget.Toast

import android.widget.Toast
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import com.example.grandvns.App.Api_Connection.Agent.agentDataItem
import com.example.grandvns.App.Api_Connection.Agent.apiInterfaceService
import com.example.grandvns.App.Navigation.Route
import com.example.grandvns.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(UnstableApi::class)
@Composable
fun Registrion(navController: NavController) {


    val flag= remember { mutableStateOf(0) }
    val temp= remember { mutableStateOf(0) }

    val selectedOptionAgent= remember { mutableStateOf("") }
    val selectedAgentType=remember{ mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.pp),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding() // Add this line
                        .verticalScroll(rememberScrollState()),

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))


                        Text(
                            text = "Let's get you started",
                            fontSize = 25.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(4.dp)

                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "You are :",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,

                            )
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(horizontal = 30.dp)
                        ) {
                            Button(
                                onClick = {
                                    flag.value = 1
                                    selectedOptionAgent.value= "Owner"
                                }, colors = if (flag.value == 1) {
                                    ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF6AB7F0),
                                        contentColor = Color.White
                                    )
                                } else
                                    ButtonDefaults.buttonColors(
                                        containerColor = Color.LightGray,
                                        contentColor = Color.White
                                    )
                            )
                            {
                                Text(text = "Owner")
                            }

                            Spacer(modifier = Modifier.width(20.dp))

                            Button(
                                onClick = { flag.value = 2
                                    selectedOptionAgent.value="Agent"
                                          }, colors = if (flag.value == 2) {
                                    ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF6AB7F0),
                                        contentColor = Color.White
                                    )
                                } else
                                    ButtonDefaults.buttonColors(
                                        containerColor = Color.LightGray,
                                        contentColor = Color.White
                                    )
                            ) {
                                Text(text = "Agent")
                            }

                            Spacer(modifier = Modifier.width(20.dp))

                            Button(
                                onClick = { flag.value = 3
                                    selectedOptionAgent.value="Builder"}, colors = if (flag.value == 3) {
                                    ButtonDefaults.buttonColors(
                                        containerColor =Color(0xFF6AB7F0),
                                        contentColor = Color.White
                                    )
                                } else
                                    ButtonDefaults.buttonColors(
                                        containerColor = Color.LightGray,
                                        contentColor = Color.White
                                    )
                            ) {
                                Text(text = "Builder")
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))


                        Text(
                            text = "You are here to :",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,

                            )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.padding(horizontal = 30.dp)
                        )
                        {
                            Button(
                                onClick = { temp.value = 1
                                          selectedAgentType.value="Sell"}, colors = if (temp.value == 1) {
                                    ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF6AB7F0),
                                        contentColor = Color.White
                                    )
                                } else
                                    ButtonDefaults.buttonColors(
                                        containerColor = Color.LightGray,
                                        contentColor = Color.White
                                    )
                            ) {
                                Text(text = "Sell")
                            }

                            Spacer(modifier = Modifier.width(30.dp))

                            Button(
                                onClick = { temp.value = 2
                                          selectedAgentType.value="Rent / Lease"}, colors = if (temp.value == 2) {
                                    ButtonDefaults.buttonColors(
                                        containerColor =Color(0xFF6AB7F0),
                                        contentColor = Color.White
                                    )
                                } else
                                    ButtonDefaults.buttonColors(
                                        containerColor = Color.LightGray,
                                        contentColor = Color.White
                                    )
                            ) {
                                Text(text = "Rent / Lease")
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    Column {

                        Text(
                            text = "Your Name:",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)

                        )
                        var name by remember { mutableStateOf("") }

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = { Text("Enter your name") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )


                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Email:",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)

                        )
                        var email by remember { mutableStateOf("") }

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text("Enter Your Email") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )


                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Contact Number:",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)


                        )
                        var contact by remember { mutableStateOf("") }

                        OutlinedTextField(
                            value = contact,
                            onValueChange = { contact = it },
                            placeholder = { Text("Enter Contact Number") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )


                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Password:",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)

                        )
                        var password by remember { mutableStateOf("") }

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = { Text("Enter Password") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )


                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "City:",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)

                        )
                        var city by remember { mutableStateOf("") }

                        OutlinedTextField(
                            value = city,
                            onValueChange = { city = it },
                            placeholder = { Text("Enter Your City") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(25.dp))
                        val gradient = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF6AB7F0), Color(0xFFC7F8F7))
                        )




                        val context = LocalContext.current
                        RegistrationGradientButton(
                            text = "Submit",
                            gradient = gradient,
                            onClick = {


                                        val postdata = agentDataItem(
                                            DOB = "2003-02-20",
                                            a_id = null, // Replace with actual data
                                            address = null, // Replace with actual data
                                            agent_type = selectedAgentType.value,// Replace with actual data
                                            altermobile = null, // Replace with actual data
                                            bankdetails = null, // Replace with actual data
                                            city = city,
                                            createdby = selectedOptionAgent.value, // Replace with actual data
                                            createdon = "2024-09-04", // Replace with actual data
                                            email = email,
                                            fcenter = null, // Replace with actual data
                                            franchiseid = null, // Replace with actual data
                                            fullname = name,
                                            mobile = contact,
                                            password = password,
                                            property_type = null // Replace with actual data
                                        )

                                //Log cat var msg show
                                        Log.d("API_REQUEST", "Request: $postdata")

                                val ApiPostdata=apiInterfaceService.PostData(postdata)

                                ApiPostdata?.enqueue(object : Callback<agentDataItem?> {
                                    override fun onResponse(
                                        call: Call<agentDataItem?>,
                                        response: Response<agentDataItem?>
                                    ) {
                                        if (response.isSuccessful) {
                                            Toast.makeText(context, "Data posted successfully", Toast.LENGTH_SHORT).show()
                                            Log.d("API_SUCCESS**", "Response: ${response.body()}")

                                            navController.navigate(Route.Login){
                                                popUpTo(navController.graph.startDestinationId)
                                                launchSingleTop=true
                                            }

                                        } else {
                                            val errorBody = response.errorBody()?.string()
                                            Toast.makeText(context, "Failed to post data: $errorBody", Toast.LENGTH_SHORT).show()
                                            Log.e("API_ERROR**", "Error: $errorBody")
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<agentDataItem?>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(context, "Network failure: ${t.message}", Toast.LENGTH_SHORT).show()
                                        Log.e("API_FAILURE", "Failure: ${t.message}", t)
                                    }
                                })



                            }

                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = "Already have an account?", fontSize = 16.sp,
                                fontWeight = FontWeight.Bold, color = Color.Black
                            )
                            TextButton(onClick = {navController.navigate(Route.Login)}) {
                                Text(text = "Login", fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,color = Color.Blue)
                            }
                        }
                    }


                }
            }
        }


@Composable
fun RegistrationGradientButton(text: String, gradient: Brush, onClick:() -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        modifier = Modifier.padding(20.dp,0.dp)
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxWidth()
                .height(52.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = Color.White)
        }
    }
}
























