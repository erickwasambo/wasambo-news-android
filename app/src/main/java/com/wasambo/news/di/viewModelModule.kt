package com.wasambo.news.di

import com.wasambo.news.ui.home.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ArticleViewModel(get()) }
}
