package com.wasambo.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wasambo.news.api.requests.NewsAPI
import com.wasambo.news.repository.ArticlesRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RequestViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsApi: NewsAPI

    private lateinit var repository: ArticlesRepository

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()
        repository = ArticlesRepository(newsApi)
    }

    @Test
    fun read_sample_success_json_file() {
        val reader = MockResponseFileReader("success_response.json")
        assertNotNull(reader.content)
    }

    @Test
    fun fetch_details_and_check_response_code_200_returned() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        runBlocking {
            val actualResponse = repository.repoGetListNewsArticles()
            assertEquals(response.toString().contains("OK"), actualResponse.status.contains("OK"))
        }
    }

    @Test
    fun `fetch details for failed response 401 returned`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
            .setBody(MockResponseFileReader("failed_response.json").content)
        mockWebServer.enqueue(response)
        runBlocking {
            val actualResponse = repository.repoGetListNewsArticles()
            assertEquals(response.toString().contains("401"), actualResponse.toString().contains("401"))
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
