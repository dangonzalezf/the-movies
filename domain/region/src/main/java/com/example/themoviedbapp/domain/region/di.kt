package com.example.themoviedbapp.domain.region

import com.example.themoviedbapp.domain.region.data.RegionRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainRegionModule = module {
    factoryOf(::RegionRepository)
}
