package com.example.themoviedbapp.feature.detail

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureDetailModule = module {
    viewModelOf(::DetailViewModel)
}
