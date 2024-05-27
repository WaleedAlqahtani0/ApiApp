package com.example.apiapp.domain.user

import com.example.apiapp.data.Repository.MoviesRepository
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(private val popularMoviesRepository: MoviesRepository) {
    suspend operator fun invoke() = popularMoviesRepository.getUserToken()
}