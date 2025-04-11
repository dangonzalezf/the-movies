import java.util.Properties

plugins {
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    id("themoviedbapp.android.application")
    id("themoviedbapp.android.application.compose")
    id("themoviedbapp.android.library.di.compose")
}

android {
    namespace = "com.example.themoviedbapp"

    defaultConfig {
        applicationId = "com.example.themoviedbapp"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.themoviedbapp.di.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").readText().byteInputStream())

        val tmdbApiKey = properties.getProperty("TMDB_API_KEY", "")
        buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    configurations.all {
        resolutionStrategy {
            force("androidx.test.ext:junit:1.2.1")
            force("androidx.test.espresso:espresso-core:3.6.1")
            force("androidx.compose.ui:ui-test-junit4:1.6.4")
        }
    }
}

dependencies {
    implementation(project(":domain:movie"))
    implementation(project(":domain:region"))
    implementation(project(":framework:core"))
    implementation(project(":framework:movie"))
    implementation(project(":framework:region"))
    implementation(project(":feature:home"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:common"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.location)
    implementation(libs.androidx.room.ktx)
    // test dependencies

    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
}
