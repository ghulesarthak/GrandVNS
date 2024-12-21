package com.example.grandvns.App.App_UI.MenuBar

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.CircularProgressIndicator
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.grandvns.App.Api_Connection.Properties.AllPropertis
import com.example.grandvns.App.Api_Connection.Properties.NetworkResponse
import com.example.grandvns.App.App_UI.MainViewModel
import com.example.grandvns.App.Navigation.Route
import com.example.grandvns.App.User_Preferences.UserPreferences
import com.example.grandvns.ui.theme.Purple
import com.example.grandvns.ui.theme.darkb1


@Composable
fun MyListingProject(
    navController: NavController,
    viewModel: MainViewModel,
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        darkb1, Color.White
                    )
                )
            )
    ) {


        LaunchedEffect(Unit) {
            viewModel.getPropertiList()
        }


        val context = LocalContext.current
        //   viewModel.Result.observeAssState().value
        when (val result = viewModel.Result.observeAsState().value) {
            is NetworkResponse.Error -> {
                Log.i("RN", result.massage)
                Toast.makeText(context, "CHECK THE NETWORK", Toast.LENGTH_SHORT).show()
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

                PropertyList(result.data, navController)
                // PropertyList(propertisList,navController,isLoading)

            }

            null -> TODO()
        }

    }
}



@Composable
fun PropertyList(propertisList: AllPropertis, navController: NavController){


    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    darkb1, Color.White
                )
            )
        )) {


        Box(
            modifier = Modifier
                // .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 110.dp, 20.dp, 0.dp)
                    .height(61.dp) // Set fixed height
                    .background(color = Color.Transparent)
                    .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(7.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Box {
                    Text(
                        text = "My Listings",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Normal, fontSize = 20.sp,

                        )
                }
                Box {
                    Button(
                        onClick = { navController.navigate(Route.PostPropeertySell) },
                        modifier = Modifier.padding(50.dp, 0.dp, 5.dp, 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(7.dp),
                        border = BorderStroke(
                            0.1.dp,
                            Color.DarkGray
                        ) // Decreased border size for Button
                    ) {
                        Text(text = "Add Property +")
                    }
                }

            }
        }





        Column {



            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    // verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val textState = remember { mutableStateOf(TextFieldValue("")) }
                    SearchView(
                        state = textState,
                        placeholder = "Search Property ...",

//                        modifier = Modifier
//                            .fillMaxWidth() // Fills the entire width of the parent
//                            .height(56.dp)  // You can set the height as needed
                    )
                    val searchtext = textState.value.text

                    val context = LocalContext.current

                    //User preference variable
                    val userPreferences = UserPreferences(context)
                    val userId = userPreferences.getUserId()

                    // Filter the list based on the  who User Login and   search text
                    val filteredList = propertisList.filter {
                        it.createdby == userId && it.toString()
                            .contains(searchtext, ignoreCase = true)
                    }



                    if (filteredList.isEmpty()) {
                        Text(
                            text = "No items found",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    else {

                        LazyColumn(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 100.dp)) {


                            itemsIndexed(items = filteredList) { _, item ->


                                Card(
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    elevation = CardDefaults.cardElevation(2.dp),
                                    onClick = {
                                        navController.navigate("Single_Listing_Project/${item.propertyid}")
                                    }
                                ) {

                                    //you have propery

                                    Surface {
                                        Row(
                                            Modifier
                                                .padding(4.dp)
                                                .fillMaxSize(),
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            var image = item.photo

                                            // Check if the image string contains brackets, meaning multiple images are listed
                                            if (image != null && image.startsWith("[") && image.endsWith(
                                                    "]"
                                                )
                                            ) {
                                                // This means it's a list of images, so clean up the string
                                                image = image.replace("[\"", "")
                                                    .replace("\"]", "")
                                                    .replace("\"", "") // Remove quotes

                                                // Split the images by commas in case there are multiple
                                                val arr = image.split(",")
                                                val arry = arr[0] // Use the first image in the list
                                                image = arry // Assign the first image to image variable
                                            }

                                            // In case it's already a clean single image
                                            image?.let { cleanImageUrl ->
                                                ImageFromUrl(
                                                    url = "https://gtsites.in/grandvns1/admin/dist/img/property/$cleanImageUrl",
                                                    180 // Pass size
                                                )
                                            }

                                            Spacer(modifier = Modifier.width(10.dp))

                                            Column(
                                                verticalArrangement = Arrangement.Center,
                                                modifier = Modifier
                                                    .padding(4.dp)
                                                    .fillMaxHeight()
                                                    .weight(0.8f)
                                            )
                                            {


                                                item.projectname?.let {
                                                    Text(
                                                        text = it,
                                                        style = MaterialTheme.typography.headlineSmall,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 20.sp
                                                    )
                                                }

                                                Row {
                                                    item.proptype?.let {
                                                        Text(
                                                            text = it,
                                                            fontWeight = FontWeight.ExtraBold,
                                                            fontSize = 17.sp,
                                                            color = Color.Gray
                                                        )
                                                    }

                                                    Text(
                                                        text = " for Sale in ",
                                                        //style = MaterialTheme.typography.headlineSmall,
                                                        fontWeight = FontWeight.Normal,
                                                        fontSize = 17.sp
                                                    )

                                                }

                                                Row {
                                                    item.locality?.let {
                                                        Text(
                                                            text = it,
                                                            style = MaterialTheme.typography.headlineSmall,
                                                            fontWeight = FontWeight.Medium,
                                                            fontSize = 16.sp,
                                                            color = Color.Gray
                                                        )
                                                    }

                                                    Text(
                                                        text = ",  ",
                                                        style = MaterialTheme.typography.headlineSmall,
                                                        fontWeight = FontWeight.Medium,
                                                        fontSize = 16.sp,
                                                        color = Color.Gray
                                                    )

                                                    item.city?.let {
                                                        Text(
                                                            text = it,
                                                            style = MaterialTheme.typography.headlineSmall,
                                                            fontWeight = FontWeight.Medium,
                                                            fontSize = 16.sp,
                                                            color = Color.Gray

                                                        )
                                                    }

                                                }


                                                Row {
                                                    Text(
                                                        text = "₹ ",
                                                        style = MaterialTheme.typography.headlineSmall,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Purple,
                                                        fontSize = 22.sp
                                                    )


                                                    item.price?.let {
                                                        Text(
                                                            text = it,
                                                            style = MaterialTheme.typography.headlineSmall,
                                                            fontWeight = FontWeight.Bold,
                                                            color = Purple,
                                                            fontSize = 22.sp
                                                        )
                                                    }

                                                }

                                                Row {


                                                    Column {


                                                        Row {
                                                            item.area?.let {
                                                                Text(
                                                                    text = it,
                                                                    style = MaterialTheme.typography.labelLarge,
                                                                    fontWeight = FontWeight.ExtraBold,
                                                                    fontSize = 14.sp
                                                                )
                                                            }

                                                            Text(
                                                                text = "  Sq.Ft",
                                                                style = MaterialTheme.typography.labelLarge,
                                                                fontWeight = FontWeight.ExtraBold,
                                                                fontSize = 14.sp
                                                            )
                                                        }

                                                        item.area_type?.let {
                                                            Text(
                                                                text = it,
                                                                style = MaterialTheme.typography.labelLarge,
                                                                fontWeight = FontWeight.ExtraBold,
                                                                color = Color.Gray,
                                                                fontSize = 14.sp
                                                            )
                                                        }

                                                    }

                                                    Spacer(modifier = Modifier.width(10.dp))


                                                    Column {


                                                        item.possession_status?.let {
                                                            Text(
                                                                text = it,
                                                                style = MaterialTheme.typography.labelLarge,
                                                                fontWeight = FontWeight.SemiBold,
                                                                color = Color.Gray,
                                                                fontSize = 10.sp
                                                            )
                                                        }

                                                        Text(
                                                            text = "possession Status",
                                                            style = MaterialTheme.typography.labelLarge,
                                                            fontWeight = FontWeight.ExtraBold,
                                                            color = Color.Gray,
                                                            fontSize = 10.sp
                                                        )

                                                    }
                                                }
                                            }
                                        }
                                    }

                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Text(
                                            text = "Posted On: ",
                                            //  style = MaterialTheme.typography.labelLarge,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.Gray
                                        )


                                        item.createdon?.let {
                                            Text(
                                                text = it,
                                                //  style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier
                                                    .padding(4.dp),
                                                //.shadow(elevation = 8.dp, ambientColor = Color.Red),
                                                color = Color.Black
                                            )
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
                }
            }
    }
}





@Composable
fun ImageFromUrl(url: String, imageSize: Int) {
    AsyncImage(
        model = url,
        contentDescription = null,


        modifier = Modifier
            .size(imageSize.dp)
            .padding(1.dp)
    )
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>, placeholder: String) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            //.fillMaxWidth()
            .padding(16.dp)
            .width(370.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(30.dp)),
        placeholder = {
            Text(text = placeholder)
        },
        leadingIcon = {
            IconButton(onClick = {
                focusManager.moveFocus(FocusDirection.Enter)
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = Color.Black, fontSize = 20.sp)
    )
}

