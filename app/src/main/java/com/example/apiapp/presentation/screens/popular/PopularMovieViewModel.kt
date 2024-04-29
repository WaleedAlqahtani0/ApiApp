package com.example.apiapp.presentation.screens.popular

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiapp.domain.popluer.GetPopularMovieUseCase
import com.example.apiapp.model.SearchResponse
import com.example.apiapp.model.UIState
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovieUseCase): ViewModel(){

    var popularMovieState: MutableState<UIState<SearchResponse>> = mutableStateOf(UIState.Loading())

    init{
        getArtWorks()
    }

    private fun getArtWorks(){
        viewModelScope.launch {
            when( val response = getPopularMovieUseCase.invoke()){
                is UIState.Success -> popularMovieState.value = UIState.Success(response.data)
                is UIState.Error -> popularMovieState.value = UIState.Error(response.error)
                is UIState.Empty -> popularMovieState.value = UIState.Empty(title =response.title)
                is UIState.Loading -> popularMovieState.value = UIState.Loading()
            }
        }
    }
}