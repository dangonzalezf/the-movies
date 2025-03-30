plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "themoviedbapp.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            id = "themoviedbapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidFeature") {
            id = "themoviedbapp.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "themoviedbapp.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "themoviedbapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryRoom") {
            id = "themoviedbapp.android.library.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("jvmLibrary") {
            id = "themoviedbapp.android.library.jvm"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("jvmRetrofitLibrary") {
            id = "themoviedbapp.android.library.jvm.retrofit"
            implementationClass = "JvmRetrofitConventionPlugin"
        }

        register("diLibrary") {
            id = "themoviedbapp.di.library"
            implementationClass = "DILibraryConventionPlugin"
        }

        register("diLibraryCompose") {
            id = "themoviedbapp.android.library.di.compose"
            implementationClass = "DiLibraryComposeConventionPlugin"
        }
    }
}
