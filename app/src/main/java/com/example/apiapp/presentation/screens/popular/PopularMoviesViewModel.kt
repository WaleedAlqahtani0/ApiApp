package com.example.apiapp.presentation.screens.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.apiapp.domain.popluer.GetPopularMoviesUseCase
import com.example.apiapp.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    var popularMoviesState: MutableStateFlow<PagingData<Results>> =
        MutableStateFlow(PagingData.empty())

    init {
        getArtWorks()
    }

    private fun getArtWorks() {
        viewModelScope.launch {
            getPopularMoviesUseCase.invoke().distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    popularMoviesState.value = it
                }
        }
    }
}

