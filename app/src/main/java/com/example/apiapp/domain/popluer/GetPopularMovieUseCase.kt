package com.example.apiapp.domain.popluer

import com.example.apiapp.data.Repository.PopularMoviesRepository
import dagger.Reusable
import javax.inject.Inject


@Reusable
class GetPopularMoviesUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) {
    suspend operator fun invoke() = popularMoviesRepository.getPopularMovies()
}