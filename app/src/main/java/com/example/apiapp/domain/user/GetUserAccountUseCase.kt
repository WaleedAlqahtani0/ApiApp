package com.example.apiapp.domain.user

import com.example.apiapp.data.Repository.MoviesRepository
import javax.inject.Inject

class GetUserAccountUseCase @Inject constructor(private val popularMoviesRepository: MoviesRepository) {
    suspend operator fun invoke(sessionId: String) =
        popularMoviesRepository.getUserAccount(sessionId)
    //to do store it in data store
}