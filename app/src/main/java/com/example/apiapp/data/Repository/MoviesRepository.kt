package com.example.apiapp.data.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.apiapp.data.paging.MoviePagingSource
import com.example.apiapp.data.remote.MovieApi
import com.example.apiapp.model.MovieDetailsResponse
import com.example.apiapp.model.Results
import com.example.apiapp.model.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    val movieApi: MovieApi
) {
    suspend fun getMovieDetails(movieID: Int): UIState<MovieDetailsResponse> {
        try {
            val response = movieApi.getMovieDetail(movieID)
            if (response.isSuccessful && response.body() != null) {
                return UIState.Success(response.body())
            } else {
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception) {
            return UIState.Error(e.message.toString())
        }

    }

    fun getPopularMovies(): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(movieApi,false)
            }
        ).flow
    }

    fun getSearchMovies(quey:String): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(movieApi,false,quey)
            }
        ).flow
    }
}

