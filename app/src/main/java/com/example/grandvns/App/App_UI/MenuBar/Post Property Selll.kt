package com.example.grandvns.App.App_UI.MenuBar

            import android.content.Context
            import android.graphics.Bitmap
            import android.media.ThumbnailUtils
            import android.net.Uri
            import android.provider.MediaStore
            import android.widget.Toast
            import androidx.activity.compose.rememberLauncherForActivityResult
            import androidx.activity.result.contract.ActivityResultContracts
            import androidx.compose.foundation.background
            import androidx.compose.foundation.layout.Arrangement
            import androidx.compose.foundation.layout.Column
            import androidx.compose.foundation.layout.ExperimentalLayoutApi
            import androidx.compose.foundation.layout.FlowRow
            import androidx.compose.foundation.layout.Row
            import androidx.compose.foundation.layout.Spacer
            import androidx.compose.foundation.layout.fillMaxSize
            import androidx.compose.foundation.layout.fillMaxWidth
            import androidx.compose.foundation.layout.height
            import androidx.compose.foundation.layout.padding
            import androidx.compose.foundation.layout.size
            import androidx.compose.foundation.layout.width
            import androidx.compose.foundation.lazy.LazyColumn
            import androidx.compose.foundation.shape.RoundedCornerShape
            import androidx.compose.material.icons.Icons
            import androidx.compose.material.icons.filled.Done
            import androidx.compose.material3.Button
            import androidx.compose.material3.ButtonDefaults
            import androidx.compose.material3.ElevatedFilterChip
            import androidx.compose.material3.ExperimentalMaterial3Api
            import androidx.compose.material3.FilterChipDefaults
            import androidx.compose.material3.Icon
            import androidx.compose.material3.IconButton
            import androidx.compose.material3.OutlinedTextField
            import androidx.compose.material3.RadioButton
            import androidx.compose.material3.RadioButtonDefaults
            import androidx.compose.material3.Text
            import androidx.compose.material3.TextField
            import androidx.compose.material3.TopAppBar
            import androidx.compose.material3.TopAppBarDefaults
            import androidx.compose.runtime.Composable
            import androidx.compose.runtime.getValue
            import androidx.compose.runtime.mutableStateOf
            import androidx.compose.runtime.remember
            import androidx.compose.runtime.setValue
            import androidx.compose.ui.Alignment

            import androidx.compose.ui.Modifier
            import androidx.compose.ui.graphics.Color
            import androidx.compose.ui.platform.LocalContext
            import androidx.compose.ui.res.painterResource
            import androidx.compose.ui.text.font.FontWeight
            import androidx.compose.ui.unit.dp
            import androidx.compose.ui.unit.sp
            import androidx.media3.common.util.Log
            import androidx.media3.common.util.UnstableApi
            import com.example.grandvns.R
            import com.example.grandvns.ui.theme.black
            import com.example.grandvns.ui.theme.darkb
            import java.text.SimpleDateFormat
            import java.util.Date


            import androidx.compose.foundation.Image
            import androidx.compose.foundation.border
            import androidx.compose.foundation.clickable
            import androidx.compose.foundation.layout.Box
            import androidx.compose.material3.CircularProgressIndicator
            import androidx.compose.material3.MaterialTheme
            import androidx.compose.runtime.mutableStateListOf
            import androidx.compose.ui.draw.blur
            import androidx.compose.ui.draw.clip
            import androidx.compose.ui.graphics.Brush
            import androidx.compose.ui.graphics.asImageBitmap
            import androidx.compose.ui.layout.ContentScale
            import androidx.navigation.NavController
            import com.example.grandvns.App.App_UI.MainViewModel

            import com.example.grandvns.App.Navigation.Route
            import com.example.grandvns.App.User_Preferences.UserPreferences
            import com.example.grandvns.ui.theme.darkb1
            import okhttp3.MediaType.Companion.toMediaTypeOrNull
            import okhttp3.MultipartBody
            import okhttp3.RequestBody
            import okhttp3.RequestBody.Companion.asRequestBody
            import okhttp3.RequestBody.Companion.toRequestBody
            import java.io.File
            import java.util.Locale


@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostPropeertySell(navController: NavController, context: Context, viewModel: MainViewModel) {

    var description by remember { mutableStateOf("") }


    val selectBuildingType = remember { mutableStateOf("") }
    val selectPropertyType = remember { mutableStateOf("") }
    val securityMaintance = remember { mutableStateOf("") }
    var selectedAmenities: String? = null
    var definingLocation : String? = null
    var explainingprice:String?=null
    var explainingtheproperty:String?=null
    var definesize:String?=null
    var suitablefor:String?=null



    var city by remember { mutableStateOf("") }

    var locality by remember { mutableStateOf("") }

    var project_Name by remember { mutableStateOf("") }

    var price by remember { mutableStateOf("") }

    var maintenance by remember { mutableStateOf("") }

    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }


    //  val context= LocalContext.current

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 100.dp,)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        darkb1, Color.White
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TopAppBar(
            title = {
                Text(
                    text = "Grand VNS Properties", color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF24486C))
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_home_work_24),
                contentDescription = null,
                tint = darkb,
                modifier = Modifier.padding(end = 8.dp, top = 20.dp)
            )
            Text(
                text = "Post Property Rent Details",
                color = darkb,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Box() {

            LazyColumn {

                item {


                    Text(
                        text = "Select Building Type :",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = black,
                        modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectBuildingType.value == "Residential",
                            onClick = { selectBuildingType.value = "Residential" },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = darkb,
                                unselectedColor = Color.DarkGray
                            )
                        )
                        Text(text = "Residential")

                        Spacer(modifier = Modifier.width(40.dp))

                        RadioButton(
                            selected = selectBuildingType.value == "Commercial",
                            onClick = { selectBuildingType.value = "Commercial" },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = darkb,
                                unselectedColor = Color.DarkGray
                            )
                        )
                        Text(text = "Commercial")
                    }


                    Spacer(modifier = Modifier.width(40.dp))


                    Text(
                        text = "Select Property Type :",
                        color = black,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectPropertyType.value == "Apartment",
                            onClick = { selectPropertyType.value = "Apartment" },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = darkb,
                                unselectedColor = Color.DarkGray
                            )
                        )
                        Text(text = "Apartment")

                        Spacer(modifier = Modifier.width(40.dp))

                        RadioButton(
                            selected = selectPropertyType.value == "Plot",
                            onClick = { selectPropertyType.value = "Plot" },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = darkb,
                                unselectedColor = Color.DarkGray
                            )
                        )
                        Text(text = "Plot")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectPropertyType.value == "Villa",
                            onClick = { selectPropertyType.value = "Villa" },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = darkb,
                                unselectedColor = Color.DarkGray
                            )
                        )
                        Text(text = "Villa")

                        Spacer(modifier = Modifier.width(83.dp))

                        RadioButton(
                            selected = selectPropertyType.value == "Penthouse",
                            onClick = { selectPropertyType.value = "Penthouse" },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = darkb,
                                unselectedColor = Color.DarkGray
                            )
                        )
                        Text(text = "Penthouse")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectPropertyType.value == "Builder Floor",
                            onClick = { selectPropertyType.value = "Builder Floor" },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = darkb,
                                unselectedColor = Color.DarkGray
                            )
                        )
                        Text(text = "Builder Floor")

                        Spacer(modifier = Modifier.width(26.dp))

                        RadioButton(
                            selected = selectPropertyType.value == "Independent House",
                            onClick = { selectPropertyType.value = "Independent House" },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = darkb,
                                unselectedColor = Color.DarkGray
                            )
                        )
                        Text(text = "Independent House")
                    }
                    Column()
                    {
                        Text(
                            text = "City :",
                            fontSize = 20.sp,
                            color = black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)

                        )

                        OutlinedTextField(
                            value = city,
                            onValueChange = { city = it },
                            label = { Text("Enter City Name") },
                            shape = RoundedCornerShape(10.dp), // Rounded corners
                            modifier = Modifier
                                .fillMaxWidth(0.9f) // Decrease the width
                                .padding(horizontal = 20.dp)
                        )


                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Locality :",
                            fontSize = 20.sp,
                            color = black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)

                        )


                        OutlinedTextField(
                            value = locality,
                            onValueChange = { locality = it },
                            label = { Text("Enter Locality") },
                            shape = RoundedCornerShape(10.dp), // Rounded corners
                            modifier = Modifier
                                .fillMaxWidth(0.9f) // Decrease the width
                                .padding(horizontal = 20.dp)
                        )


                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Project / Building Name :",
                            fontSize = 20.sp,
                            color = black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)

                        )


                        OutlinedTextField(
                            value = project_Name,
                            onValueChange = { project_Name = it },
                            label = { Text("Enter Project / Building Name") },
                            shape = RoundedCornerShape(10.dp), // Rounded corners
                            modifier = Modifier
                                .fillMaxWidth(0.9f) // Decrease the width
                                .padding(horizontal = 20.dp)
                        )


                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Rent (Per Month) :",
                            fontSize = 20.sp,
                            color = black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)

                        )

                        OutlinedTextField(
                            value = price,
                            onValueChange = { price = it },
                            label = { Text("Enter Property Price") },
                            shape = RoundedCornerShape(10.dp), // Rounded corners
                            modifier = Modifier
                                .fillMaxWidth(0.9f) // Decrease the width
                                .padding(horizontal = 20.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Maintenance :",
                            fontSize = 20.sp,
                            color = black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)

                        )

                        OutlinedTextField(
                            value = maintenance,
                            onValueChange = { maintenance = it },
                            label = { Text("Enter Property Maintenance") },
                            shape = RoundedCornerShape(10.dp), // Rounded corners
                            modifier = Modifier
                                .fillMaxWidth(0.9f) // Decrease the width
                                .padding(horizontal = 20.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Security Deposit :",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = securityMaintance.value == "Zero Month",
                                onClick = { securityMaintance.value = "Zero Month" },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = darkb,
                                    unselectedColor = Color.DarkGray
                                )
                            )
                            Text(text = "Zero Month")

                            Spacer(modifier = Modifier.width(26.dp))

                            RadioButton(
                                selected = securityMaintance.value == "One Month",
                                onClick = { securityMaintance.value = "One Month" },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = darkb,
                                    unselectedColor = Color.DarkGray
                                )
                            )
                            Text(text = "One Month")
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = securityMaintance.value == "Two Month",
                                onClick = { securityMaintance.value = "Two Month" },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = darkb,
                                    unselectedColor = Color.DarkGray
                                )
                            )
                            Text(text = "Two Month")

                            Spacer(modifier = Modifier.width(26.dp))

                            RadioButton(
                                selected = securityMaintance.value == "Other",
                                onClick = { securityMaintance.value = "Other" },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = darkb,
                                    unselectedColor = Color.DarkGray
                                )
                            )
                            Text(text = "Other")
                        }

                        Spacer(modifier = Modifier.height(16.dp))


                        Text(
                            text = "Property Picture :",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)
                        )


                        // val context = LocalContext.current
                        var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
                        var selectedImageFile by remember { mutableStateOf<File?>(null) }
                        var bitmapImage by remember { mutableStateOf<Bitmap?>(null) }
                        val imagePickerLauncher =
                            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                                selectedImageUri = uri
                                uri?.let {
                                    val filePath = getRealPathFromURI(it, context)
                                    selectedImageFile = File(filePath)
                                    bitmapImage =
                                        MediaStore.Images.Media.getBitmap(
                                            context.contentResolver,
                                            it
                                        )
                                }
                            }


                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .padding(16.dp)
                        ) {
                            // Display selected or default image
                            if (bitmapImage != null) {
                                Image(
                                    bitmap = bitmapImage!!.asImageBitmap(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                                )
                            } else {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color.LightGray)
                                        .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.imagegallery),
                                        contentDescription = "Image select from gallary",
                                        modifier = Modifier.fillParentMaxSize()
                                    )
                                }
                            }



                            Spacer(modifier = Modifier.height(16.dp))


                            Button(
                                onClick = { imagePickerLauncher.launch("image/*") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF6AB7F0),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 8.dp,
                                    pressedElevation = 12.dp
                                ),
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .height(40.dp)
                            ) {
                                Text(
                                    text = "Select Image",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }




                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Add Video :",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)
                        )

                        // State for video selection
                        var selectedVideoUri by remember { mutableStateOf<Uri?>(null) }
                        var selectedVideoFile by remember { mutableStateOf<File?>(null) }

                        val videoPickerLauncher =
                            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                                selectedVideoUri = uri
                                uri?.let {
                                    val filePath = getRealPathFromURI(it, context)
                                    selectedVideoFile = File(filePath)
                                }
                            }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .padding(16.dp)
                        ) {

// Display selected video thumbnail or placeholder
                            if (selectedVideoUri != null) {
                                val thumbnail = ThumbnailUtils.createVideoThumbnail(
                                    selectedVideoUri.toString(),
                                    MediaStore.Video.Thumbnails.MINI_KIND
                                )
                                thumbnail?.let {
                                    Image(
                                        bitmap = it.asImageBitmap(),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(200.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                                    )
                                }
                            } else {
//                            Box(
//                                contentAlignment = Alignment.Center,
//                                modifier = Modifier
//                                    .size(200.dp)
//                                    .clip(RoundedCornerShape(12.dp))
//                                    .background(Color.LightGray)
//                                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
//                            ) {
//                                Image(painter = painterResource(id = R.drawable.imagegallery), contentDescription ="Image select from gallary", modifier = Modifier.fillParentMaxSize() )
//                            }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Button to select video
                            Button(
                                onClick = { videoPickerLauncher.launch("video/*") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF6AB7F0),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 8.dp,
                                    pressedElevation = 12.dp
                                ),
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .height(40.dp)
                            ) {
                                Text(
                                    text = "Select Video",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Amenities :", fontSize = 25.sp,
                            modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                            fontWeight = FontWeight.ExtraBold,
                            color = black,
                        )


                        Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp))
                        {
                            // Aminity = FilterChips1()
                            selectedAmenities = FilterChips1().joinToString(", ")
                        }



                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                        )
                        {
                            Text(
                                text = "Define Your Property:",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                                color = black,
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Please choose a keyword that defines the property clearly",
                                color = Color.Gray,
                                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "1. Defining Location",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                                color = black,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            definingLocation = PropertyType1().joinToString(", ")



                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = "2. Explaning Price",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                                color = black,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))


                            explainingprice = ExploringPrice1().joinToString(", ")


                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = "3. Explaining the Property",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                                color = black,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            explainingtheproperty = ExplainProperty1().joinToString(", ")

                            Spacer(modifier = Modifier.height(14.dp))



                            Text(
                                text = "4. Defining Size",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                                color = black,
                                fontWeight = FontWeight.Bold
                            )

                            definesize = DefineSize1().joinToString(", ")

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "5. Suitable for",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                                color = black,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            suitablefor = SuitableFor1().joinToString(", ")

                            Text(
                                text = "Property Description",
                                fontSize = 25.sp,
                                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                                color = black,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "Please write a detailed description about property so clients can understand property better or generate using our AI tool.\n",
                                fontSize = 14.sp,
                                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium,
                            )

                            TextField(
                                value = description,
                                modifier = Modifier.padding(50.dp, 0.dp, 50.dp, 0.dp),
                                onValueChange = { description = it },
                                label = { Text("Describe your description") }
                            )
                        }
                        val sdf = SimpleDateFormat("'Date\n'yyyy-MM-dd '\n\nand\n\nTime\n'HH:mm:ss")
                        val currentDateAndTime = sdf.format(Date())


                        val photoParts = selectedImageUris.map { uri ->
                            val file = File(uri.path!!)
                            val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                            MultipartBody.Part.createFormData("photo[]", file.name, requestBody)
                        }

                        Spacer(modifier = Modifier.height(28.dp))

                        var context = LocalContext.current
                        // val isLoading by remember { mutableStateOf(viewModel.isLoading) }


                        Box(
                            modifier = Modifier.fillParentMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {


//                        // Loading Indicator
//                        if (viewModel.isLoadingp) {
//                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
//                        }


                            Button(
                                onClick = {
                                    // Ensure that the selected image file is initialized
                                    if (selectedImageFile != null) {
                                        // Prepare image file for upload as Multipart
                                        val requestFile =
                                            selectedImageFile!!.asRequestBody("image/*".toMediaTypeOrNull())
                                        val photoPart = MultipartBody.Part.createFormData(
                                            "photo",  // This should match the name expected by your API for the image
                                            selectedImageFile!!.name,
                                            requestFile
                                        )
                                        // Prepare video file if it exists
                                        val videoPart: MultipartBody.Part? =
                                            if (selectedVideoFile != null) {
                                                val videoRequestFile =
                                                    selectedVideoFile!!.asRequestBody("video/*".toMediaTypeOrNull())
                                                MultipartBody.Part.createFormData(
                                                    "video",  // This should match the name expected by your API for the video
                                                    selectedVideoFile!!.name,
                                                    videoRequestFile
                                                )
                                            } else {
                                                null // If there's no video, set to null
                                            }

                                        //create preference object
                                        val userPreferences = UserPreferences(context)
                                        val userId = userPreferences.getUserId()

                                        // Create your form data (converting each field into RequestBody)
                                        val formData = mutableMapOf<String, RequestBody>().apply {
                                            this["buildtype"] =
                                                selectBuildingType.value.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["proptype"] =
                                                selectPropertyType.value.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["city"] =
                                                city.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["projectname"] =
                                                project_Name.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["locality"] =
                                                locality.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["price"] =
                                                price.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["area"] =
                                                "".toRequestBody("text/plain".toMediaTypeOrNull()) // or any area value
                                            this["area_type"] =
                                                "Carpet Area".toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["additionalrooms"] =
                                                "btnpoojaroom,btnservantroom".toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["ageofprop"] =
                                                "5-7".toRequestBody("text/plain".toMediaTypeOrNull()) // Change as needed
                                            this["amenities"] =
                                                selectedAmenities!!.toRequestBody("text/plain".toMediaTypeOrNull())
                                                    ?: "".toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["defininglocation"] =
                                                definingLocation!!.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["explainingprice"] =
                                                explainingprice!!.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["explainingtheproperty"] =
                                                explainingtheproperty!!.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["definesize"] =
                                                definesize!!.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["suitablefor"] =
                                                suitablefor!!.toRequestBody("text/plain".toMediaTypeOrNull())
                                            this["balcony"] =
                                                "Connected".toRequestBody("text/plain".toMediaTypeOrNull()) // Change as needed
                                            this["facing"] =
                                                "South-West".toRequestBody("text/plain".toMediaTypeOrNull()) // Change as needed
                                            this["description"] =
                                                description.toRequestBody("text/plain".toMediaTypeOrNull())
                                            // Add any other fields as necessary
                                            this["createdby"] =
                                                userId!!.toRequestBody("text/plain".toMediaTypeOrNull())
                                            // this["createdon"] = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()).toRequestBody("text/plain".toMediaTypeOrNull())
                                        }


                                        // Debugging: Log the values of each field
                                        Log.d(
                                            "API Call Debug",
                                            "Building Type: ${selectBuildingType.value}"
                                        )
                                        Log.d(
                                            "API Call Debug",
                                            "Property Type: ${selectPropertyType.value}"
                                        )
                                        Log.d("API Call Debug", "City: $city")
                                        Log.d("API Call Debug", "Project Name: $project_Name")
                                        Log.d("API Call Debug", "Locality: $locality")
                                        Log.d("API Call Debug", "Price: $price")
                                        Log.d("API Call Debug", "Area: 850") // or any area value
                                        Log.d("API Call Debug", "Area Type: Carpet Area")
                                        Log.d(
                                            "API Call Debug",
                                            "Additional Rooms: btnpoojaroom,btnservantroom"
                                        )
                                        Log.d(
                                            "API Call Debug",
                                            "Age of Property: 5-7"
                                        ) // Change as needed
                                        Log.d(
                                            "API Call Debug",
                                            "Amenities: ${selectedAmenities?.toString() ?: "None"}"
                                        )
                                        Log.d(
                                            "API Call Debug",
                                            "Balcony: Connected"
                                        ) // Change as needed
                                        Log.d(
                                            "API Call Debug",
                                            "Facing: South-West"
                                        ) // Change as needed
                                        Log.d("API Call Debug", "Description: $description")
                                        Log.d("API Call Debug", "Created By: 2")
                                        Log.d(
                                            "API Call Debug",
                                            "Created On: ${
                                                SimpleDateFormat(
                                                    "yyyy-MM-dd HH:mm:ss",
                                                    Locale.getDefault()
                                                ).format(Date())
                                            }"
                                        )


                                        viewModel.postPropertiList(
                                            photoPart,
                                            videoPart,
                                            formData,
                                            context
                                        )
                                        // postdataList(photoPart,formData,context,navController,_flatNO,mobile)

                                    } else {
                                        // If no image is selected, show a message
                                        Toast.makeText(
                                            context,
                                            "Please select an image first",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }, enabled = !viewModel.isLoadingp // Disable button when loading
                                , modifier = Modifier
                                    .padding(50.dp, 0.dp, 50.dp, bottom = 50.dp)
                                    .width(300.dp)
                                    .height(50.dp)
                            ) {
                                Text(text = "Submit", fontSize = 15.sp)
                            }


                        }

                    }
                }
            }
            // Show loading overlay when isLoading is true
            if (viewModel.isLoadingp) {
                // Block all touch events and interactions with the UI during loading
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)) // Semi-transparent overlay
                        //.then( Modifier.blur(16.dp)) // Apply blur only when loading
                        .clickable(enabled = false) {} // Prevent interactions while loading
                ) {
                    // Show loading spinner
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                        strokeWidth = 6.dp
                    )
                }
            }
        }
    }
}





@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterChips1(): List<String> {
    val selectedChips = remember { mutableStateListOf<String>() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val chips = listOf(
            "Restaurant", "24 x 7 security", "Power Backup", "Lift", "Gymnasium",
            "Club House", "Swimming Pool", "Attached Market", "Central AC", "Speeded Elevator",
            "Pre-School", "Medical Facility", "Care Center", "Pet Area", "Indoor Games",
            "Conference Room", "Large Green Area"
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            chips.forEach { chip ->
                ElevatedFilterChip(
                    selected = selectedChips.contains(chip),
                    onClick = {
                        if (selectedChips.contains(chip)) selectedChips.remove(chip)
                        else selectedChips.add(chip)
                    },
                    label = { Text(text = chip) },
                    leadingIcon = if (selectedChips.contains(chip)) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else null,
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        containerColor = if (selectedChips.contains(chip)) Color.Black else Color.White
                    )
                )
            }
        }
    }

    return selectedChips
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PropertyType1(): List<String> {
    // Using mutableStateListOf to hold the selected types
    val selectedTypes = remember { mutableStateListOf<String>() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val types = listOf(
            "School in vicinity",
            "Adjoining Metrostation",
            "Peaceful Vicinity",
            "Wide Road",
            "Near City Center",
            "Safe & Secure Locality"
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            types.forEach { type ->
                ElevatedFilterChip(
                    selected = selectedTypes.contains(type),
                    onClick = {
                        // Toggle the selected state
                        if (selectedTypes.contains(type)) {
                            selectedTypes.remove(type)  // Deselect
                        } else {
                            selectedTypes.add(type)     // Select
                        }
                    },
                    label = { Text(text = type) },
                    leadingIcon = if (selectedTypes.contains(type)) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else null,
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        containerColor = if (selectedTypes.contains(type)) Color.Black else Color.White
                    )
                )
            }
        }
    }
    // Return selected types as a List<String> for API submission
    return selectedTypes
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExploringPrice1(): List<String> {
    val selectedPrices = remember { mutableStateListOf<String>() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val prices = listOf(
            "Desperate Sell", "Breakthrough Price", "Quick Deal",
            "Investment Opportunity", "High Rental Yield", "Affordable"
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            prices.forEach { price ->
                ElevatedFilterChip(
                    selected = selectedPrices.contains(price),
                    onClick = {
                        if (selectedPrices.contains(price)) {
                            selectedPrices.remove(price)
                        } else {
                            selectedPrices.add(price)
                        }
                    },
                    label = { Text(text = price) },
                    leadingIcon = if (selectedPrices.contains(price)) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null
                            )
                        }
                    } else null,
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        containerColor = if (selectedPrices.contains(price)) Color.Black else Color.White
                    )


                )
            }
        }
    }

    // Return the selected price list for further use
    return selectedPrices
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExplainProperty1(): List<String> {
    // Use mutableStateListOf instead of mutableStateMapOf for managing selected chips
    val selectedProperties = remember { mutableStateListOf<String>() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val properties = listOf(
            "Reputed builder", "Well ventilated", "Fully Renovated", "Vastu Compliant",
            "Spacious", "Ample Parking", "Free Hold", "Gated Society"
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Add space between chips
            modifier = Modifier.fillMaxWidth()
        ) {
            properties.forEach { property ->
                ElevatedFilterChip(
                    selected = selectedProperties.contains(property),
                    onClick = {
                        if (selectedProperties.contains(property)) {
                            selectedProperties.remove(property)
                        } else {
                            selectedProperties.add(property)
                        }
                    },
                    label = { Text(text = property) },
                    leadingIcon = if (selectedProperties.contains(property)) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else null,
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        containerColor = if (selectedProperties.contains(property)) Color.Black else Color.White
                    )
                )
            }
        }
    }

    // Return the selected properties list for further use
    return selectedProperties
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DefineSize1(): List<String> {
    // Use mutableStateListOf to store the selected sizes
    val selectedSizes = remember { mutableStateListOf<String>() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val sizes = listOf(
            "Tasteful Interiors", "Prime Location", "Luxury Lifestyle",
            "Well Maintained", "Plenty of Sunlight", "Newly Built"
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Add space between chips
            modifier = Modifier.fillMaxWidth()
        ) {
            sizes.forEach { size ->
                ElevatedFilterChip(
                    selected = selectedSizes.contains(size),
                    onClick = {
                        if (selectedSizes.contains(size)) {
                            selectedSizes.remove(size)
                        } else {
                            selectedSizes.add(size)
                        }
                    },
                    label = { Text(text = size) },
                    leadingIcon = if (selectedSizes.contains(size)) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else null,
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        containerColor = if (selectedSizes.contains(size)) Color.Black else Color.White
                    )
                )
            }
        }
    }

    // Return the selected sizes list for further use
    return selectedSizes
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SuitableFor1(): List<String> {
    // Use mutableStateListOf to manage multiple selections
    val selectedSuitableFor = remember { mutableStateListOf<String>() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val suitableForOptions = listOf("Family", "Bachelor", "Couple", "Female Only")

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            suitableForOptions.forEach { option ->
                ElevatedFilterChip(
                    selected = selectedSuitableFor.contains(option),
                    onClick = {
                        if (selectedSuitableFor.contains(option)) {
                            selectedSuitableFor.remove(option)
                        } else {
                            selectedSuitableFor.add(option)
                        }
                    },
                    label = { Text(text = option) },
                    leadingIcon = if (selectedSuitableFor.contains(option)) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else null,
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        containerColor = if (selectedSuitableFor.contains(option)) Color.Black else Color.White
                    )
                )
            }
        }
    }

    // Return the selected suitable for options for future use
    return selectedSuitableFor
}







// Helper function to convert the URI to file path
fun getRealPathFromURI(contentUri: Uri, context: Context): String? {
    var result: String? = null
    val cursor = context.contentResolver.query(contentUri, null, null, null, null)
    cursor?.use {
        val index = it.getColumnIndex(MediaStore.Images.Media.DATA)
        if (index >= 0) {
            it.moveToFirst()
            result = it.getString(index)
        }
    }
    return result
}




// Helper function to create RequestBody from String
//fun createPartFromString(value: String): RequestBody {
//    return RequestBody.create("text/plain".toMediaTypeOrNull(), value)
//}



fun createPartFromString(value: String): RequestBody {
    return value.toRequestBody("text/plain".toMediaTypeOrNull())
}
