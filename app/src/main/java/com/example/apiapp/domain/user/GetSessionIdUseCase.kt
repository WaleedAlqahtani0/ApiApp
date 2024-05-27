package com.example.apiapp.domain.user

import coil.request.ImageRequest
import com.example.apiapp.data.Repository.MoviesRepository
import javax.inject.Inject

class GetSessionIdUseCase @Inject constructor(private val popularMoviesRepository: MoviesRepository) {
    suspend operator fun invoke(requestToken: String) =
        popularMoviesRepository.getSessionId(requestToken)
}