package com.example.apiapp.data.Repository

import com.example.apiapp.data.remote.MovieApi
import com.example.apiapp.model.UIState
import com.example.apiapp.model.SearchResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PopularMoviesRepository @Inject constructor(
    val movieApi: MovieApi
) {
    suspend fun getPopularMovies(): UIState<SearchResponse> {
        return try {
            val response = movieApi.getUpcoming()
            if (response.isSuccessful && response.body() != null) {
                UIState.Success(response.body())
            } else {
                UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception) {
            UIState.Error(e.message.toString())
        }

    }

}