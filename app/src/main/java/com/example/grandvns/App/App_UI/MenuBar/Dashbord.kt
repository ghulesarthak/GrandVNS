package com.example.grandvns.App.App_UI.MenuBar

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.grandvns.App.Api_Connection.Properties.AllPropertis
import com.example.grandvns.App.Api_Connection.Properties.AllPropertisItem
import com.example.grandvns.App.Api_Connection.Properties.NetworkResponse
import com.example.grandvns.App.App_UI.MainViewModel
import com.example.grandvns.App.User_Preferences.UserPreferences
import com.example.grandvns.ui.theme.Purple
import com.example.grandvns.ui.theme.darkb
import com.example.grandvns.ui.theme.darkb1
import com.example.grandvns.ui.theme.vilot
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Dashbord(
    propertisList: List<AllPropertisItem>,
    navController: NavHostController,
    isLoading: Boolean, // Add a parameter to receive the loading state
    viewModel: MainViewModel,
    paddingValues: PaddingValues

) {
    Scaffold {it
        Box(
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

            val context = LocalContext.current
            //   viewModel.Result.observeAssState().value
            val result = viewModel.Result.observeAsState().value

            when (result) {
                is NetworkResponse.Error -> {
                    Log.i("RN", result.massage)
                    Toast.makeText(context, "CHEK THE NETWORK", Toast.LENGTH_SHORT).show()
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

                    PropertyList(result.data, navController, isLoading)
                    // PropertyList(propertisList,navController,isLoading)

                }

                null -> TODO()
            }
        }
    }
}




@Composable
fun PropertyList(
    propertisList: AllPropertis,
    navController: NavController,
    isLoading: Boolean
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    var numberOfProperty: Int? = null
    var soldPropertyCount: Int? = null


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        darkb1, Color.White
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        Box(
            modifier = Modifier
                // .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(61.dp) // Set fixed height
                    .background(color = Color.Transparent)
            ) {


            }
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .verticalScroll(rememberScrollState()), // Enable vertical scroll for the entire content
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
                )
                {


                    SearchView(
                        state = textState,
                        placeholder = "Search Property ...",
                    )
                    val searchtext = textState.value.text


                    // User preferences to get userId
                    val context = LocalContext.current
                    val userPreferences = UserPreferences(context)
                    val userId =
                        userPreferences.getUserId()  // Retrieve the logged-in user's ID

                    // Filter the list based on the search text and userId
                    val filteredList = propertisList.filter {
                        it.createdby == userId && it.toString().contains(
                            searchtext,
                            ignoreCase = true
                        ) // Only show properties that belong to the user
                    }


                    //Count the number of property
                    numberOfProperty = filteredList.size

                    // Count the number of sold properties
                    soldPropertyCount = filteredList.count { it.status == "sold" }


                    // Sort the filtered list by `propertyid` or by date in descending order
                    val sortedList = filteredList.sortedByDescending { item ->
                        item.propertyid?.toIntOrNull()
                            ?: 0 // Convert propertyid to integer, sort by it
                    }


                    Text(
                        text = "Latest Properties",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = darkb,
//                        modifier = Modifier
//                            .graphicsLayer(
//                                shadowElevation = 8f, // The elevation value creates the shadow
//                                shape = RoundedCornerShape(8.dp), // Optional: adds rounding to the text background
//                                clip = true // Clipping to the rounded shape
//                            )
                        // fontSize = 22.sp
                    )


                    // Take the latest 4 properties after sorting
                    val latestProperties = sortedList.take(4)


                    if (latestProperties.isEmpty()) {
                        // Show "No items found" message if the filtered list is empty
                        Text(
                            text = "No Properties Yet",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        val lazyListState = rememberLazyListState()
                        val coroutineScope = rememberCoroutineScope()


                        // LaunchedEffect to automatically scroll every 3 seconds


                        // LaunchedEffect to automatically scroll every 3 seconds
                        LaunchedEffect(key1 = lazyListState) {
                            while (true) {
                                delay(3000L) // Delay for 3 seconds
                                val totalItems = latestProperties.size

                                // If it's the last visible item, reset the scroll to the first item
                                val nextIndex =
                                    if (lazyListState.firstVisibleItemIndex == totalItems - 1) {
                                        0 // Go back to the first item after reaching the end
                                    } else {
                                        lazyListState.firstVisibleItemIndex + 1 // Scroll to the next item
                                    }

                                coroutineScope.launch {
                                    // lazyListState.animateScrollToItem(nextIndex)
                                    // Smooth scrolling with animation over a duration
                                    lazyListState.animateScrollToItem(
                                        index = nextIndex,
                                        scrollOffset = 0 // Optional: set a specific offset if needed
                                    )
                                }
                            }
                        }

                        LazyRow(
                            modifier = Modifier
                                //.padding(0.dp, 0.dp, 15.dp, 0.dp,)
                                .fillMaxWidth(),
                            state = lazyListState //pass to state to lazy row
                        ) {


                            itemsIndexed(items = latestProperties) { index, item ->

                                PropertyCard(item = item, navController)
                                Spacer(modifier = Modifier.width(20.dp))
                            }
                        }

                    }
                }






                Spacer(modifier = Modifier.height(40.dp))

                Column() {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        OutlinedCard(
                            colors = cardColors(
                                containerColor = Color.White,
                            ),
                            onClick = {},
                            modifier = Modifier
                                .size(width = 160.dp, height = 100.dp)
                                .shadow(elevation = 7.dp)

                        ) {


                            OutlinedCard(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(width = 160.dp, height = 30.dp)
                                    .fillMaxSize(),
                                colors = cardColors(
                                    containerColor = darkb
                                ),
                            ) {
                                BasicText(

                                    text = "No Of Property",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    ),

                                    modifier = Modifier
                                        .padding(7.dp)
                                        .align(Alignment.CenterHorizontally)
                                        .align(Alignment.CenterHorizontally)


                                )
//                                Text(text = "Base Amount is ")
//
                            }

                            Text(
                                text = numberOfProperty.toString(),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 15.dp)
                                    .align(alignment = Alignment.CenterHorizontally)
                            )


                        }


//                    Spacer(modifier = Modifier.padding(30.dp))
                        OutlinedCard(
                            colors = cardColors(
                                containerColor = Color.White,
                            ),
                            onClick = {},
                            modifier = Modifier
                                .size(width = 160.dp, height = 100.dp)
                                .shadow(elevation = 8.dp)
                        ) {

                            OutlinedCard(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(width = 160.dp, height = 30.dp)
                                    .fillMaxSize()
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .align(alignment = AbsoluteAlignment.Right),
                                colors = cardColors(
                                    containerColor = darkb
                                ),


                                ) {
                                BasicText(
                                    text = "No of Sold Property",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    ),

                                    modifier = Modifier
                                        .padding(7.dp)
                                        .align(Alignment.CenterHorizontally)
                                        .align(Alignment.CenterHorizontally)


                                )

                            }
                            Text(
                                text = soldPropertyCount.toString(), modifier = Modifier
                                    .padding(top = 15.dp)
                                    .align(alignment = Alignment.CenterHorizontally)
                            )
                        }

                    }


                    //Row2

                    Spacer(modifier = Modifier.padding(1.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly

                    ) {


                        OutlinedCard(

                            modifier = Modifier
                                .size(width = 160.dp, height = 100.dp)
                                .shadow(elevation = 8.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = cardColors(
                                containerColor = Color.White,
                            ),

                            )
                        {

                            OutlinedCard(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(width = 160.dp, height = 30.dp)
                                    .fillMaxSize()
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .align(alignment = AbsoluteAlignment.Right),
                                colors = cardColors(
                                    containerColor = Color(0xFFFF435F)
                                ),


                                ) {
                                BasicText(
                                    text = "CCCC",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    ),

                                    modifier = Modifier
                                        .padding(7.dp)
                                        .align(Alignment.CenterHorizontally)
                                        .align(Alignment.CenterHorizontally)

                                )

                            }
                            Text(
                                text = "DDDD",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 15.dp)
                                    .align(alignment = Alignment.CenterHorizontally)
                            )
                        }

                        OutlinedCard(
                            colors = cardColors(
                                containerColor = Color.White
                            ),
                            // onClick = {},
                            modifier = Modifier
                                .size(width = 160.dp, height = 100.dp)
                                .shadow(elevation = 8.dp)
                        ) {
                            OutlinedCard(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(width = 160.dp, height = 30.dp)
                                    .fillMaxSize()
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .align(alignment = AbsoluteAlignment.Right)
                                    .shadow(15.dp, ambientColor = Color.Blue),
                                colors = cardColors(
                                    containerColor = Color(0xFF25C9FA)
                                ),


                                ) {
                                BasicText(
                                    text = "DDDD",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    ),

                                    modifier = Modifier
                                        .padding(7.dp)
                                        .align(Alignment.CenterHorizontally)
                                        .align(Alignment.CenterHorizontally)

                                )


                            }
                            Text(
                                text = "DDDD",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 15.dp)
                                    .align(alignment = Alignment.CenterHorizontally)
                            )
                        }

                    }


                    //Row3

                    Spacer(modifier = Modifier.padding(1.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        OutlinedCard(
                            colors = cardColors(
                                containerColor = Color.White,
                            ),
                            onClick = {},
                            modifier = Modifier
                                .size(width = 160.dp, height = 100.dp)
                                .shadow(elevation = 7.dp)

                        ) {


                            OutlinedCard(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(width = 160.dp, height = 30.dp)
                                    .fillMaxSize(),
                                colors = cardColors(
                                    containerColor = darkb
                                ),
                            ) {
                                BasicText(

                                    text = "EEEE",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    ),

                                    modifier = Modifier
                                        .padding(7.dp)
                                        .align(Alignment.CenterHorizontally)
                                        .align(Alignment.CenterHorizontally)


                                )
//                                Text(text = "Base Amount is ")
//
                            }
                            Text(
                                text = "EEEE",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 15.dp)
                                    .align(alignment = Alignment.CenterHorizontally)
                            )


                        }
//                    Spacer(modifier = Modifier.padding(30.dp))
                        OutlinedCard(
                            colors = cardColors(
                                containerColor = Color.White,
                            ),
                            onClick = {},
                            modifier = Modifier
                                .size(width = 160.dp, height = 100.dp)
                                .shadow(elevation = 8.dp)
                        ) {

                            OutlinedCard(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(width = 160.dp, height = 30.dp)
                                    .fillMaxSize()
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .align(alignment = AbsoluteAlignment.Right),
                                colors = cardColors(
                                    containerColor = darkb
                                ),


                                ) {
                                BasicText(
                                    text = "FFFF",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    ),

                                    modifier = Modifier
                                        .padding(7.dp)
                                        .align(Alignment.CenterHorizontally)
                                        .align(Alignment.CenterHorizontally)


                                )

                            }
                            Text(
                                text = "FFFF", modifier = Modifier
                                    .padding(top = 15.dp)
                                    .align(alignment = Alignment.CenterHorizontally)
                            )
                        }

                    }
                }






                Spacer(modifier = Modifier.height(40.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "All Properties",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = darkb,

                        )




                    if (propertisList.isEmpty()) {
                        // Show "No items found" message if the filtered list is empty
                        Text(
                            text = "No items found",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {

                        val lazyListState = rememberLazyListState()
                        val coroutineScope = rememberCoroutineScope()


                        // LaunchedEffect to automatically scroll every 3 seconds


                        // LaunchedEffect to automatically scroll every 3 seconds
                        LaunchedEffect(key1 = lazyListState) {
                            while (true) {
                                delay(3000L) // Delay for 3 seconds
                                val totalItems = propertisList.size

                                // If it's the last visible item, reset the scroll to the first item
                                val nextIndex =
                                    if (lazyListState.firstVisibleItemIndex == totalItems - 1) {
                                        0 // Go back to the first item after reaching the end
                                    } else {
                                        lazyListState.firstVisibleItemIndex + 1 // Scroll to the next item
                                    }

                                coroutineScope.launch {
                                    lazyListState.animateScrollToItem(nextIndex)
                                }
                            }
                        }
                        LazyRow(
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 0.dp, 130.dp,)
                                .fillMaxWidth(),
                            state = lazyListState
                        ) {


                            itemsIndexed(items = propertisList) { index, item ->

                                PropertyCard(item = item, navController)


                            }
                        }
                    }
                }
            }
        }
    }
}

/*
    @Composable
    fun PropertyCard(item: AllPropertisItem, navController: NavController) {

        val screenWidth = LocalConfiguration.current.screenWidthDp

        Card(
            shape = RoundedCornerShape(8.dp),

            modifier = Modifier
               // .fillMaxWidth()
                .width( if (screenWidth < 430)350.dp  else 420.dp  )//
                .height(300.dp)
                //.fillMaxWidth()
                .padding(30.dp, 20.dp), colors = cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp),
            onClick = {
                navController.navigate("Single_Listing_Project/${item.propertyid}")
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {


//
//                Column(
//                    Modifier
//                        .padding(4.dp)
//                        .fillMaxSize(),
//                   horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
                //  Column (Modifier.fillMaxWidth().height()){


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
                    ImageFromUrl2(
                        url = "https://gtsites.in/grandvns1/admin/dist/img/property/$cleanImageUrl",
                        110// Pass size
                    )
                }

                //  }
                //  Spacer(modifier = Modifier.width(120.dp))

                Row(
                   // verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        //.width(400.dp)
                        // .clip(RoundedCornerShape(8.dp))
                        //  .fillMaxWidth()
                        .padding(0.dp, 150.dp, 0.dp, 0.dp,)
                        .fillMaxSize()
                        .background(color = vilot) , verticalAlignment =Alignment.Bottom
                    // contentScale = ContentScale.Crop
                    // .weight(0.8f)
                ) {



                    Column(modifier = Modifier.padding(10.dp)) {


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
                                    style = MaterialTheme.typography.headlineSmall,

                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 17.sp,
                                    color = Color.Black
                                )
                            }

                            Text(
                                text = " for Sale in ",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Normal,
                                fontSize = 17.sp
                            )


//                                item.locality?.let {
//                                    Text(
//                                        text = it,
//                                        style = MaterialTheme.typography.headlineSmall,
//                                        fontWeight = FontWeight.Normal,
//                                        fontSize = 16.sp,
//                                        color = Color.Black
//                                    )
//                                }
//
//                                Text(
//                                    text = ", ",
//                                    style = MaterialTheme.typography.headlineSmall,
//                                    fontWeight = FontWeight.Normal,
//                                    fontSize = 17.sp,
//                                    color = Color.Black
//                                )

                            item.city?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 17.sp,
                                    color = Color.Black

                                )
                            }


                        }
                    }
                }
            }

        }

    }

    @Composable
    fun ImageFromUrl2(url: String, height: Int) {
        AsyncImage(
            model = url,
            contentDescription = null,


//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(1.dp)
            modifier = Modifier
                //.size(200.dp)
                // .height(150.dp)
                //.width(300.dp)
                .fillMaxSize()
                // .clip(RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp)) // Clipping the image for a rounded effect
            , contentScale = ContentScale.Crop

        )
    }
*/
/*
@Composable
fun PropertyCard(item: AllPropertisItem, navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // Adjust dimensions based on screen size
    val cardWidth = screenWidth * 0.9f
    val cardHeight = screenHeight * 0.4f

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = {
            navController.navigate("Single_Listing_Project/${item.propertyid}")
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            var image = item.photo
            if (image != null && image.startsWith("[") && image.endsWith("]")) {
                image = image.replace("[\"", "").replace("\"]", "").replace("\"", "")
                val arr = image.split(",")
                image = arr[0]
            }

            // Image display
            image?.let { cleanImageUrl ->
                ImageFromUrl2(
                    url = "https://gtsites.in/grandvns1/admin/dist/img/property/$cleanImageUrl",
                    height = (cardHeight * 0.5f).toDp // Half the card height for the image
                )
            }

            // Info section
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = (cardHeight * 0.5f).toDp()) // Position below image
                    .background(color = Color(0xFF6A0DAD)), // Background color for info
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
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
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 17.sp,
                                color = Color.Black
                            )
                        }

                        Text(
                            text = " for Sale in ",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Normal,
                            fontSize = 17.sp
                        )

                        item.city?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Medium,
                                fontSize = 17.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageFromUrl2(url: String, height: Int) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}
*/

@Composable
fun PropertyCard(item: AllPropertisItem, navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // Adjust dimensions based on screen size
    val cardWidth = screenWidth * 1f
    val cardHeight = screenHeight * 0.3f

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
              .padding(30.dp, 10.dp), colors = cardColors(containerColor = Color.White),
      //  colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = {
            navController.navigate("Single_Listing_Project/${item.propertyid}")
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            var image = item.photo
            if (image != null && image.startsWith("[") && image.endsWith("]")) {
                image = image.replace("[\"", "").replace("\"]", "").replace("\"", "")
                val arr = image.split(",")
                image = arr[0]
            }

            // Image display
            image?.let { cleanImageUrl ->
                ImageFromUrl2(
                    url = "https://gtsites.in/grandvns1/admin/dist/img/property/$cleanImageUrl",
                    height = cardHeight * 1f // Half the card height for the image
                )
            }

            // Info section
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = cardHeight * 0.5f) // Position below image
                    .background(color =vilot), // Background color for info
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
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
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 17.sp,
                                color = Color.Black
                            )
                        }

                        Text(
                            text = " for Sale in ",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Normal,
                            fontSize = 17.sp
                        )

                        item.city?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Medium,
                                fontSize = 17.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageFromUrl2(url: String, height: Dp) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}
