////package com.example.grandvns.App.App_UI
////
////import android.net.Uri
////import androidx.activity.compose.rememberLauncherForActivityResult
////import androidx.activity.result.contract.ActivityResultContracts
////import androidx.compose.foundation.Image
////import androidx.compose.foundation.layout.Arrangement
////import androidx.compose.foundation.layout.Box
////import androidx.compose.foundation.layout.Column
////import androidx.compose.foundation.layout.fillMaxSize
////import androidx.compose.foundation.layout.fillMaxWidth
////import androidx.compose.material3.Button
////import androidx.compose.material3.Text
////import androidx.compose.runtime.Composable
////import androidx.compose.runtime.getValue
////import androidx.compose.runtime.mutableStateOf
////import androidx.compose.runtime.remember
////import androidx.compose.runtime.setValue
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.layout.ContentScale
////import androidx.compose.ui.unit.dp
////import coil.compose.AsyncImage
////
/////*
////
////@Composable
////fun Splash() {
//////    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
//////        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "App_Logo", modifier = Modifier.size(120.dp))
//////
//////    }
//////
//////    LaunchedEffect(true) {
//////        delay(3000)
//////
//////        navController.navigate(Route.Login)
//////    }
////
////