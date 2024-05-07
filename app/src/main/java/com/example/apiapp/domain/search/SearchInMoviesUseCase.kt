package com.example.apiapp.domain.search

import com.example.apiapp.data.Repository.MoviesRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SearchInMoviesUseCase @Inject constructor(private val popularMoviesRepository: MoviesRepository) {
    operator fun invoke(query: String)= popularMoviesRepository.getSearchMovies(query)

}