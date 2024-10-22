package com.example.themoviedbapp.framework.remote

import com.example.themoviedbapp.data.model.RemoteMovie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    val page: Int,
    @SerialName("results") val remoteMovies: List<RemoteMovie>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)
