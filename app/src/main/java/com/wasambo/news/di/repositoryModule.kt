package com.wasambo.news.di

import com.wasambo.news.repository.ArticlesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ArticlesRepository(get()) }
}