package com.wasambo.news.api.requests

import com.wasambo.news.BuildConfig
import com.wasambo.news.api.models.articles.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/svc/mostpopular/v2/viewed/1.json")
    suspend fun listArticles(@Query("api-key") apiKey: String = BuildConfig.API_KEY): ArticlesResponse
}
