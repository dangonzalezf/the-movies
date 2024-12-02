package com.example.themoviedbapp.domain.region.data

import com.example.themoviedbapp.domain.region.entities.Location

interface RegionDataSource {
    suspend fun findLastRegion(): String
    suspend fun Location.toRegion(): String
}
