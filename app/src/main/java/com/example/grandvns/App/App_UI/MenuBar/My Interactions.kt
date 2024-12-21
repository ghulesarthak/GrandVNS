package com.example.grandvns.App.App_UI.MenuBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyInteraction(){


    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().align(Alignment.Center), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "MyInteraction",
                //  style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(4.dp),
                //.shadow(elevation = 8.dp, ambientColor = Color.Red),
                color = Color.Black,
                fontSize = 150.sp
            )
        }
    }
}