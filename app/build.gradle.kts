plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.grandvns"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.grandvns"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.compose.material)
    //implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("androidx.compose.material3:material3:1.1.0-alpha01")




    //nav Controller dependancy
    implementation ("androidx.navigation:navigation-compose:2.7.6")

    //implementation("com.android.support:appcompat-v7:28.0.0")
    implementation ("com.google.code.gson:gson:2.9.1")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")



    implementation ("androidx.compose.ui:ui:1.0.0")
    implementation ("androidx.compose.material:material:1.0.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation ("androidx.activity:activity-compose:1.3.0")



   // implementation("io.coil-kt:coil-compose:2.2.0")
    //implementation ("io.coil-kt:coil-compose:2.4.0")


    //implementation("io.coil-kt:coil-compose:1.3.2")


        implementation ("androidx.compose.ui:ui:1.2.0")
        implementation ("androidx.compose.material3:material3:1.0.0")
    //coil
        implementation ("io.coil-kt:coil-compose:2.4.0")

  //  coil ="2.7.0"



implementation(libs.coil)
    //Button and IMNAge And Vidio Add
    implementation ("androidx.activity:activity-ktx:1.3.1")
    implementation ("androidx.fragment:fragment-ktx:1.3.6")

    //camera
    implementation("com.github.CanHub:Android-Image-Cropper:4.0.0")





//glans Dependancy for widgets

    implementation ("androidx.glance:glance:1.0.0-alpha05")
    implementation ("androidx.glance:glance-appwidget:1.0.0-alpha05")


    

    //***************************

    //Api retrofit dependancy
    implementation ("com.squareup.retrofit2:retrofit:2.6.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation ("com.google.android.material:material:1.1.0")


//
//    //prefarance dependancy login state data store
//    implementation ("androidx.datastore:datastore-preferences:1.0.0")
//



    //Serialize the List to a String (using JSON or another method)
    implementation ("com.google.code.gson:gson:2.8.8")

//okHTTP3 dependancy
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")


    //live data
    implementation("androidx.compose.runtime:runtime-livedata:1.6.8")

    //for blure effect
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.1")


}

