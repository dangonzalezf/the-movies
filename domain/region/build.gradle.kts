plugins {
    id("themoviedbapp.android.library.jvm")
    id("themoviedbapp.di.library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
