package com.example.themoviedbapp.framework.region

import android.location.Geocoder
import com.example.themoviedbapp.domain.region.data.LocationDataSource
import com.example.themoviedbapp.domain.region.data.RegionDataSource
import com.example.themoviedbapp.domain.region.entities.Location

class GeocoderRegionDataSource(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) : RegionDataSource {

    companion object {
        const val DEFAULT_REGION = "US"
    }

    override suspend fun findLastRegion(): String = locationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    override suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }
}
