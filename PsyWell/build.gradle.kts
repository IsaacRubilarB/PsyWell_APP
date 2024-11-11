plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4") // Plugin de Gradle para Android
        classpath("com.google.gms:google-services:4.3.10") // Plugin de Google Services (para Firebase)
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
