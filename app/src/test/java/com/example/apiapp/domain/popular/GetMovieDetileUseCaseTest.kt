package com.example.apiapp.domain.popular

import com.example.apiapp.data.Repository.MoviesRepository
import com.example.apiapp.domain.detail.GetMovieDetailUseCase
import com.example.apiapp.model.MovieDetailsResponse
import com.example.apiapp.model.UIState
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class GetMovieDetileUseCaseTest :BaseTestCase() {

    @MockK(relaxed = true)
    lateinit var movieRepository: MoviesRepository

    @MockK(relaxed = true)
    lateinit var getMovieDetileUseCase: GetMovieDetailUseCase


    @Before
    override fun setup() {
        super.setup()
        getMovieDetileUseCase = GetMovieDetailUseCase(movieRepository)
    }

    @After
    override fun tearDown(){
        super.tearDown()
    }

    val dummy = UIState.Success(MovieDetailsResponse())


     @Test
     fun invoke() {
         runTest{
             val expected = dummy
             coEvery {
                 movieRepository.getMovieDetails(11)
             } returns expected
             val result = getMovieDetileUseCase(11)
             assertEquals(expected, result)
         }
     }


}