package com.example.apiapp.domain.detail



import com.example.apiapp.data.Repository.MoviesRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val popularMoviesRepository: MoviesRepository) {
    suspend operator fun invoke(id: Int) = popularMoviesRepository.getMovieDetails(id)

}