package com.example.themoviedbapp

import android.app.Application
import com.example.themoviedbapp.domain.movie.DomainMovieModule
import com.example.themoviedbapp.domain.region.DomainRegionModule
import com.example.themoviedbapp.feature.detail.FeatureDetailModule
import com.example.themoviedbapp.feature.home.FeatureHomeModule
import com.example.themoviedbapp.framework.core.frameworkCoreModule
import com.example.themoviedbapp.framework.movie.FrameworkMovieModule
import com.example.themoviedbapp.framework.region.FrameworkRegionModule
import com.example.themoviedbapp.framework.region.frameworkRegionModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ksp.generated.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG) // Logea en el logcat lo que va ocurriendo con koin
            androidContext(this@App)
            modules(
                appModule,
                FeatureHomeModule().module,
                FeatureDetailModule().module,
                DomainMovieModule().module,
                DomainRegionModule().module,
                frameworkCoreModule,
                FrameworkMovieModule().module,
                frameworkRegionModule,
                FrameworkRegionModule().module
            )
        }
    }
}

val appModule = module {
    single(named("apiKey")) { BuildConfig.TMDB_API_KEY }
}
