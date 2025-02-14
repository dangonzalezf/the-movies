plugins {
    id("themoviedbapp.android.library.jvm")
    //id("themoviedbapp.di.library")
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.kotlinx.coroutines.core)
}
