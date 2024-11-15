package com.example.themoviedbapp.data.datasource

import com.example.themoviedbapp.domain.Location

interface RegionDataSource {
    suspend fun findLastRegion(): String

    suspend fun Location.toRegion(): String
}
