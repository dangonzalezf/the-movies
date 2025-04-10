plugins {
    id("themoviedbapp.android.library.jvm")
}

dependencies {
    implementation(project(":domain:movie"))
    implementation(project(":domain:region"))
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
}
