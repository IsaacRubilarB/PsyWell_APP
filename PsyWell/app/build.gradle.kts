plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.psywell"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.psywell"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Firebase dependencies
    implementation("com.google.firebase:firebase-auth:21.0.1") // Firebase Authentication
    implementation("com.google.firebase:firebase-firestore:24.3.0") // Firebase Firestore
    implementation("com.google.firebase:firebase-database:20.0.5") // Firebase Realtime Database (si lo usas)
    implementation("com.google.firebase:firebase-storage:20.0.1") // Firebase Storage (si lo usas)
        // Google Sign-In dependencies
        implementation ("com.google.android.gms:play-services-auth:20.4.1") // Asegúrate de tener la última versión

    // Add the Google services plugin
    implementation("com.google.gms:google-services:4.3.10") // Este debe estar aquí

    // AndroidX and other libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Testing libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // AndroidX support libraries
    implementation(libs.androidx.appcompat)
}

// Apply plugin at the end
apply(plugin = "com.google.gms.google-services") // Este se debe agregar al final del archivo
