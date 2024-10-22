package com.example.themoviedbapp.data.remote

import com.example.themoviedbapp.BuildConfig
import okhttp3.Interceptor

fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
    chain
        .request()
        .newBuilder()
        .url(
            chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()
        )
        .build()
)
