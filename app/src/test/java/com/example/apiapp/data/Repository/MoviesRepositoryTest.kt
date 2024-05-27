package com.example.apiapp.data.Repository

import com.example.apiapp.BuildConfig
import com.example.apiapp.contest
import com.example.apiapp.data.remote.MovieApi
import com.example.apiapp.model.MovieDetailsResponse
import com.example.apiapp.model.SearchResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesRepositoryTest {
    private lateinit var server: MockWebServer
    private lateinit var api : MovieApi


    @Before
    fun setUp() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url("/${contest.MOVIE_BASE_URL}"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getMovieDetails()  = runTest{
        val dto =MovieDetailsResponse()
        val gson: Gson = GsonBuilder().create()
        val json = gson.toJson(dto)
        val res = MockResponse()
        res.setBody(json)
        server.enqueue(res)
        val data = api.getMovieDetail(938614, BuildConfig.TMDM_API_KEY)
        server.takeRequest()
        assertEquals(data.body(), dto)
    }

    @Test
    fun `getMvieDetails, return error`() = runTest {
        val res = MockResponse()
        res.setResponseCode(404)
        res.setBody("404")
        server.enqueue(res)

        val data = api.getMovieDetail(938614)
        server.takeRequest()
        assertTrue(res.status.contains(data.raw().message,true))
        assertEquals(data.isSuccessful, false)
    }


    @Test
    fun getUpComingMovies() = runTest {
        val dto = SearchResponse()
        val gson: Gson = GsonBuilder().create()
        val json = gson.toJson(dto)
        val res = MockResponse()
        res.setBody(json)
        server.enqueue(res)
        val data = api.getUpcoming()
        server.takeRequest()
        assertEquals(data.body(), dto)
    }
}
//    @Test
//    fun searchInMovies() {
//    }
//}