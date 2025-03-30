plugins {
    id("themoviedbapp.android.library")
    id("themoviedbapp.di.library")
}

android {
    namespace = "com.example.themoviedbapp.framework.region"
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.play.services.location)
}
