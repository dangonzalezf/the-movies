package com.example.themoviedbapp.framework

import android.annotation.SuppressLint
import com.example.themoviedbapp.data.datasource.LocationDataSource
import com.example.themoviedbapp.domain.Location
import android.location.Location as AndroidLocation
import com.google.android.gms.location.FusedLocationProviderClient
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine

class PlayServiceLocationDataSource(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationDataSource {
    override suspend fun lastLocation(): Location? = fusedLocationClient.lastLocation()
}

@SuppressLint("MissingPermission")
private suspend fun FusedLocationProviderClient.lastLocation(): Location? {
    return suspendCancellableCoroutine { continuation ->
        lastLocation.addOnSuccessListener { location ->
            continuation.resume(location?.toDomainLocation())
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}

private fun AndroidLocation.toDomainLocation(): Location? = Location(latitude, longitude)
