package com.example.themoviedbapp

import android.app.Application
import com.example.themoviedbapp.domain.movie.domainMovieModule
import com.example.themoviedbapp.domain.region.domainRegionModule
import com.example.themoviedbapp.feature.detail.featureDetailModule
import com.example.themoviedbapp.feature.home.featureHomeModule
import com.example.themoviedbapp.framework.core.frameworkCoreModule
import com.example.themoviedbapp.framework.movie.frameworkMovieModule
import com.example.themoviedbapp.framework.region.frameworkRegionModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG) // Logea en el logcat lo que va ocurriendo con koin
            androidContext(this@App)
            modules(
                appModule,
                domainMovieModule,
                domainRegionModule,
                featureDetailModule,
                featureHomeModule,
                frameworkCoreModule,
                frameworkMovieModule,
                frameworkRegionModule,
            )
        }
    }
}

val appModule = module {
    single(named("apiKey")) { BuildConfig.TMDB_API_KEY }
}
