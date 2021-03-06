import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
}


android {
    compileSdk = ProjectConfigs.compileSdk

    defaultConfig {
        applicationId = ProjectConfigs.applicationId
        minSdk = ProjectConfigs.minSdk
        targetSdk = ProjectConfigs.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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
        jvmTarget = ProjectConfigs.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfigs.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = ProjectConfigs.buildToolsVersion
}

dependencies {
    kapt("com.google.dagger:hilt-android-compiler:${Version.hilt}")
    implementation(project(mapOf("path" to ":blue_library")))
    appDependencies()
    unitTestDependencies()
    androidTestDependencies()
}