package com.example.apiapp.domain.popluer

import androidx.paging.PagingData
import com.example.apiapp.data.Repository.MoviesRepository
import com.example.apiapp.model.Results
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class GetPopularMoviesUseCase @Inject constructor(private val popularMoviesRepository: MoviesRepository) {
    operator fun invoke(): Flow<PagingData<Results>> {
        return popularMoviesRepository.getPopularMovies()
    }


}