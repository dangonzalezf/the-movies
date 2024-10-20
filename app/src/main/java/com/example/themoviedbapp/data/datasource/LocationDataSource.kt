package com.example.themoviedbapp.data.datasource

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine

interface LocationDataSource {
    @SuppressLint("MissingPermission")
    suspend fun lastLocation(): Location?
}

class PlayServiceLocationDataSource(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationDataSource {

    @SuppressLint("MissingPermission")
    override suspend fun lastLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                continuation.resume(location)
            }.addOnFailureListener {
                continuation.resume(null)
            }
        }
    }
}
