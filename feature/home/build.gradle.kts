plugins {
    id("themoviedbapp.android.feature")
    id("themoviedbapp.android.library.di.compose")
}

android {
    namespace = "com.example.themoviedbapp.feature.home"
}

dependencies {
    implementation(project(":domain:movie"))
    implementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(project(":test:unit"))
}
 
