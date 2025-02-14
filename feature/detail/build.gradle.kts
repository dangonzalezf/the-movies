plugins {
    id("themoviedbapp.android.feature")
    //id("themoviedbapp.android.library.di.compose")
}

android {
    namespace = "com.example.themoviedbapp.feature.detail"
}

dependencies {
    implementation(project(":domain:movie"))
}
