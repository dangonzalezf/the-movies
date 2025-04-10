plugins {
    id("themoviedbapp.android.library.jvm")
    id("themoviedbapp.di.library")
}

dependencies {
    implementation(project(":domain:region"))
    testImplementation(project(":test:unit"))
}
