package com.example.themoviedbapp.framework.region

import android.annotation.SuppressLint
import android.location.Location
import com.example.themoviedbapp.domain.region.data.LocationDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine

class PlayServiceLocationDataSource(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationDataSource {
    override suspend fun findLastLocation(): com.example.themoviedbapp.domain.region.entities.Location? = fusedLocationClient.lastLocation()
}

@SuppressLint("MissingPermission")
private suspend fun FusedLocationProviderClient.lastLocation(): com.example.themoviedbapp.domain.region.entities.Location? {
    return suspendCancellableCoroutine { continuation ->
        lastLocation.addOnSuccessListener { location ->
            continuation.resume(location?.toDomainLocation())
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}

private fun Location.toDomainLocation(): com.example.themoviedbapp.domain.region.entities.Location? =
    com.example.themoviedbapp.domain.region.entities.Location(latitude, longitude)



