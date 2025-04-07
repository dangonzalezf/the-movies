package com.example.themoviedbapp.domain.region.data

import com.example.themoviedbapp.domain.region.entities.Location

const val DEFAULT_REGION = "US"

interface RegionDataSource {
    suspend fun findLastRegion(): String
    suspend fun Location.toRegion(): String
}
