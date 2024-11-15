package com.example.themoviedbapp.data.datasource

import android.annotation.SuppressLint
import com.example.themoviedbapp.domain.Location

interface LocationDataSource {
    @SuppressLint("MissingPermission")
    suspend fun lastLocation(): Location?
}
