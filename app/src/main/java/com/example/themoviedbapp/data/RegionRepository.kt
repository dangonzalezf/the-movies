package com.example.themoviedbapp.data

import com.example.themoviedbapp.data.datasource.RegionDataSource

class RegionRepository(private val regionDataSource: RegionDataSource) {
    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()
}
