plugins {
    id("themoviedbapp.android.library.compose")
}

android {
    namespace = "com.example.themoviedbapp.feature.common"
}

dependencies {
    implementation(libs.androidx.activity.compose)
}
