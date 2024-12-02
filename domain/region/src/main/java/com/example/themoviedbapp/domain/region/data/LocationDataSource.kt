package com.example.themoviedbapp.domain.region.data

import com.example.themoviedbapp.domain.region.entities.Location

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}
