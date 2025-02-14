plugins {
    id("themoviedbapp.android.feature")
    //id("themoviedbapp.android.library.di.compose")
}

android {
    namespace = "com.example.themoviedbapp.feature.home"
}

dependencies {
    implementation(project(":domain:movie"))
}
 
