package com.example.themoviedbapp.framework.region

import android.app.Application
import android.content.Context
import android.location.Geocoder
import com.example.themoviedbapp.domain.region.data.LocationDataSource
import com.example.themoviedbapp.domain.region.data.RegionDataSource
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkRegionBindsModule {

    @Binds
    abstract fun bindLocationDataSource(locationDataSource: PlayServiceLocationDataSource): LocationDataSource

    @Binds
    abstract fun bindRegionDataSource(regionDataSource: GeocoderRegionDataSource): RegionDataSource
}

@Module
@InstallIn(SingletonComponent::class)
internal class FrameworkRegionModule {

    @Provides
    fun providesFusedLocationClient(app: Application) = LocationServices.getFusedLocationProviderClient(app)

    @Provides
    fun providesGeocoder(app: Application) = Geocoder(app)
}
