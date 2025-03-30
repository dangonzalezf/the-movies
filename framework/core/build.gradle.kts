plugins {
    id("themoviedbapp.android.library")
    id("themoviedbapp.android.library.room")
    id("themoviedbapp.android.library.jvm.retrofit")
    id("themoviedbapp.di.library")
}

android {
    namespace = "com.example.themoviedbapp.framework.core"
}

dependencies {
    implementation(project(":framework:movie"))
}
