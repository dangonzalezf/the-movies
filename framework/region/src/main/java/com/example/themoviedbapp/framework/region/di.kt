package com.example.themoviedbapp.framework.region

import android.content.Context
import android.location.Geocoder
import com.example.themoviedbapp.domain.region.data.LocationDataSource
import com.example.themoviedbapp.domain.region.data.RegionDataSource
import com.google.android.gms.location.LocationServices
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val frameworkRegionModule = module {
    factoryOf(::PlayServiceLocationDataSource) bind LocationDataSource::class
    factory { LocationServices.getFusedLocationProviderClient(get<Context>()) }
    factoryOf(::GeocoderRegionDataSource) bind RegionDataSource::class
    factory { Geocoder(get()) }
}
