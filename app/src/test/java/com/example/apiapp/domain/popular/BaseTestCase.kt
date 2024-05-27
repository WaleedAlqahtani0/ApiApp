package com.example.apiapp.domain.popular

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import io.mockk.MockKAnnotations



open class BaseTestCase{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    open fun setup(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    open fun tearDown(){
        Dispatchers.resetMain()
        unmockkAll()
    }


}