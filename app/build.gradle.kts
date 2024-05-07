plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    alias(libs.plugins.googleGmsGoogleServices)
}

android {
    namespace = "com.example.mydoctor"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mydoctor"
        minSdk = 24
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
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    
//    splash api
    implementation ("androidx.core:core-splashscreen:1.0.1")

//    for additional icons like visibility
    implementation ("androidx.compose.material:material-icons-extended:1.6.7" )

//    preferences-datastore
    implementation ("androidx.datastore:datastore-preferences:1.1.1")


//    DaggerHilt
    implementation ("com.google.dagger:hilt-android:2.51")
    ksp ("com.google.dagger:hilt-compiler:2.51")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    //Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")



    // Import the Firebase BoM
//    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
//
//    // When using the BoM, you don't specify versions in Firebase library dependencies
//
//    // Add the dependency for the Firebase SDK for Google Analytics
//    implementation("com.google.firebase:firebase-analytics")
//
//    // TODO: Add the dependencies for any other Firebase products you want to use
//    // See https://firebase.google.com/docs/android/setup#available-libraries
//    // For example, add the dependencies for Firebase Authentication and Cloud Firestore
//    implementation("com.google.firebase:firebase-auth:23.0.0")
//    implementation("com.google.android.gms:play-services-auth:21.1.1")
//    implementation("com.google.firebase:firebase-firestore")
//
//
 }