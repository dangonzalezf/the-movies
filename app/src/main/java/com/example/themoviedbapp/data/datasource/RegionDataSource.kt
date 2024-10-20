package com.example.themoviedbapp.data.datasource

import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.example.themoviedbapp.common.getFromLocationCompat

const val DEFAULT_REGION = "US"

interface RegionDataSource {
    suspend fun findLastRegion(): String

    suspend fun Location.toRegion(): String
}

class GeocoderRegionDataSource(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) : RegionDataSource {

    override suspend fun findLastRegion(): String = locationDataSource.lastLocation()?.toRegion() ?: DEFAULT_REGION

    override suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }
}
