package com.example.themoviedbapp.di

import android.app.Application
import androidx.room.Room
import com.example.themoviedbapp.framework.core.FrameworkCoreModule.FrameworkCoreExtrasModule
import com.example.themoviedbapp.framework.core.MoviesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FrameworkCoreExtrasModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideDataBase(app: Application): MoviesDataBase {
        val db = Room.inMemoryDatabaseBuilder(
            app,
            MoviesDataBase::class.java
        )
            .setTransactionExecutor(Dispatchers.Main.asExecutor())
            .allowMainThreadQueries()
            .build()
        return db
    }
}
