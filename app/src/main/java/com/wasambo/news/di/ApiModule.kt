package com.wasambo.news.di

import com.wasambo.news.api.requests.NewsAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(NewsAPI::class.java) }
}