package com.example.grandvns.App.App_UI.MenuBar
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.grandvns.App.Api_Connection.Agent.agentDataItem
import com.example.grandvns.App.Api_Connection.Properties.AllPropertisItem
import com.example.grandvns.App.User_Preferences.UserPreferences
import com.example.grandvns.R
import com.example.grandvns.ui.theme.darkb1


@Composable
 fun Single_Listing_Project(selectedProperty: AllPropertisItem, AgentList: List<agentDataItem>) {

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
            .padding(0.dp, 110.dp)
            .verticalScroll(rememberScrollState())
        //  .verticalScroll(rememberScrollState()) // Enable scrolling for long content
        , verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Image Slider
        //   PropertyImageSlider(photoString = selectedProperty.photo)


        var image = selectedProperty.photo
        if (image != null) {
            image = image.replace("[\"", "")
        }
        if (image != null) {
            image = image.replace("\"]", "")
        }
        if (image != null) {
            image = image.replace("\"", "")
        }

        val arr = image!!.split(",")
        val arry = arr[0]



        ImageFromUrl1(
            url = "https://gtsites.in/grandvns1/admin/dist/img/property/$arry",
            300
        )




        Column(
            modifier = Modifier
                .fillMaxSize()
                // .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Property Image
           // PropertyImage(selectedProperty.photo)

         //   Spacer(modifier = Modifier.height(16.dp))

            var createdby=selectedProperty.createdby
            Text(text = createdby.toString())

            // State to store the searched user
           // var selectedUser by remember { mutableStateOf<agentDataItem?>(null) }
            // Filter the list based on the search text and userId


//this is code get a a who create a property Agent info
           var selectedUser = AgentList.find { it.a_id == selectedProperty.createdby }
            var mobileNo=selectedUser!!.mobile

            Text(text = mobileNo.toString())

            
            // Property Title and Location
            PropertyTitle(selectedProperty)
            

            Spacer(modifier = Modifier.height(8.dp))

            // Property Price
            Text(
                text = "₹ ${selectedProperty.price}",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color(0xFF6200EE)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Property Info: Rooms, Area, etc.
            PropertyInfoSection(selectedProperty)

            Spacer(modifier = Modifier.height(16.dp))

            // Request Call and WhatsApp buttons
            RequestButtons(mobileNo)
            // Button with an image background
          //  ButtonWithImageBackground()

            Spacer(modifier = Modifier.height(16.dp))



            Spacer(modifier = Modifier.height(24.dp))

            // Key Highlights Section

            PropertyKeyHighlights(selectedProperty)

            Spacer(modifier = Modifier.height(24.dp))

            // Property Information Section
            PropertyInformationSection(selectedProperty)

            Spacer(modifier = Modifier.height(24.dp))

            // Amenities Section
            AmenitiesSection(selectedProperty)
        }
    }
}

@Composable
fun PropertyImage(photo: String?) {
    val image = photo?.let {
        it.replace("[\"", "").replace("\"]", "").split(",")[0]
    } ?: ""
    Image(
        painter = rememberAsyncImagePainter("https://gtsites.in/grandvns1/admin/dist/img/property/$image"),
        contentDescription = null,
        modifier = Modifier
            .height(240.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}
@Composable
fun ImageFromUrl1(url: String, height: Int) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = null,
        modifier = Modifier
            .height(height.dp)
            .fillMaxWidth()
//            .clip(RoundedCornerShape(8.dp)),
       , contentScale = ContentScale.Crop
    )
}

@Composable
fun PropertyTitle(selectedProperty: AllPropertisItem) {
    Text(
        text = selectedProperty.projectname ?: "Project Name",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
    Text(
        text = "${selectedProperty.proptype} in ${selectedProperty.locality}, ${selectedProperty.city}",
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.Gray
    )
}

@Composable
fun PropertyInfoSection(selectedProperty: AllPropertisItem) {
    Column(
        verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.baseline_maps_home_work_24), // Reference your drawable here
                contentDescription = "Custom Icon",
                //  modifier = Modifier.size(48.dp) // Set size for the icon
            )
            Text(text = "${selectedProperty.no_of_rooms} Rooms", fontWeight = FontWeight.Medium)
        }
        Row {
            Image(
                painter = painterResource(id = R.drawable.baseline_bathtub_24), // Reference your drawable here
                contentDescription = "Custom Icon",
              //  modifier = Modifier.size(48.dp) // Set size for the icon
            )
            Text(text = "${selectedProperty.no_of_baths} Bathrooms", fontWeight = FontWeight.Medium)
        }


        Row {
            Text(text = "${selectedProperty.area} Sq.ft", fontWeight = FontWeight.Medium)
        }
        Row {
            Text(text = "Carpet Area", fontWeight = FontWeight.Light)
        }

    }
}

@Composable
fun RequestButtons(mobileNo: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val context = LocalContext.current
        UserPreferences(context)

        val buttonModifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .height(50.dp)

        val textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        val iconSize = 32.dp

        // WhatsApp Button
        Button(
            onClick = {
                val url = "https://wa.me/${mobileNo}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            },
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val iconImage: Painter = painterResource(id = R.drawable.whatsapp_icon)
                Image(
                    painter = iconImage,
                    contentDescription = "WhatsApp Icon",
                    modifier = Modifier.size(iconSize)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "WhatsApp", style = textStyle)
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Quick Enquiry Button
        Button(
            onClick = {
                val phoneNumber = mobileNo
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                context.startActivity(intent)
            },
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val iconImage: Painter = painterResource(id = R.drawable.phonecall)
                Image(
                    painter = iconImage,
                    contentDescription = "Phone Icon",
                    modifier = Modifier.size(iconSize)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Quick Enquiry", style = textStyle)
            }
        }
    }

}

@Composable
fun PropertyKeyHighlights(selectedProperty: AllPropertisItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Text(text = "Key Highlights", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Terrace", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = selectedProperty.ageofprop ?: "", fontWeight = FontWeight.Medium)
        }
        // Add more key highlights as needed
    }
}

@Composable
fun PropertyInformationSection(selectedProperty: AllPropertisItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row { Text(text = "Property Type: ${selectedProperty.proptype}", fontWeight = FontWeight.Medium) }
            Row { Text(text = "City: ${selectedProperty.city}", fontWeight = FontWeight.Medium) }
            Row { Text(text = "Possession: ${selectedProperty.possession_status}", fontWeight = FontWeight.Medium) }
            Row { Text(text = "Furnishing: ${selectedProperty.furnishing_status}", fontWeight = FontWeight.Medium) }
            Row { Text(text = "Rooms: ${selectedProperty.no_of_rooms}", fontWeight = FontWeight.Medium) }
            Row { Text(text = "Covered Parking: ${selectedProperty.coveredparking}", fontWeight = FontWeight.Medium) }
            // Add more details as needed...
        }
    }
}

@Composable
fun AmenitiesSection(selectedProperty: AllPropertisItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            selectedProperty.amenities?.let {
                Text(text = "Amenities: ${parseAmenities(it).joinToString(", ")}", fontWeight = FontWeight.Medium)
            }
        }
    }
}

// Helper function to parse amenities
fun parseAmenities(amenitiesJson: String): List<String> {
    return amenitiesJson.replace("[", "")
        .replace("]", "")
        .replace("\"", "")
        .split(",")
        .map { it.trim() }
}

