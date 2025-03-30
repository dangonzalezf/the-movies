package com.example.themoviedbapp.feature.home

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureHomeModule = module {
    viewModelOf(::HomeViewModel)
}
