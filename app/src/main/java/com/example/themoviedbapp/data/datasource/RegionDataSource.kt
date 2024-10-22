package com.example.themoviedbapp.data.datasource

import android.location.Location

interface RegionDataSource {
    suspend fun findLastRegion(): String

    suspend fun Location.toRegion(): String
}
