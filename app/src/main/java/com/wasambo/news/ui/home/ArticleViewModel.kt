package com.wasambo.news.ui.home

import androidx.lifecycle.*
import com.wasambo.news.api.models.articles.Article
import com.wasambo.news.repository.ArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ArticleViewModel(private val articlesRepository: ArticlesRepository) : ViewModel() {
    val articleList = MutableLiveData<List<Article>>()
    val errorMessage = MutableLiveData<String>()
    fun vmGetArticleList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = articlesRepository.repoGetListNewsArticles().articles
                    articleList.postValue(result)
                } catch (throwable: Throwable) {
                    when (throwable) {
                        is IOException -> {
                            errorMessage.postValue("Network Error")
                        }
                        is HttpException -> {
                            val codeError = throwable.code()
                            val errorMessageResponse = throwable.message()
                            errorMessage.postValue("Error $errorMessageResponse : $codeError")
                        }
                        else -> {
                            errorMessage.postValue("Unknown error")
                        }
                    }
                }
            }
        }
    }
}
