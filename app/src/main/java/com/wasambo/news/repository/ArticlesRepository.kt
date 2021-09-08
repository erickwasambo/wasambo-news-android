package com.wasambo.news.repository

import com.wasambo.news.api.requests.NewsAPI

class ArticlesRepository(private val newsAPI: NewsAPI) {
    suspend fun repoGetListNewsArticles() = newsAPI.listArticles()
}
