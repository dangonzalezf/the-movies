package com.example.themoviedbapp.data.datasource

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine

class LocationDataSource(app: Application) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(app)

    @SuppressLint("MissingPermission")
    suspend fun lastLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                continuation.resume(location)
            }.addOnFailureListener {
                continuation.resume(null)
            }
        }
    }
}
