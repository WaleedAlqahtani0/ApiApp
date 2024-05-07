package com.example.apiapp.presentation.screens.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.apiapp.domain.popluer.GetPopularMoviesUseCase
import com.example.apiapp.domain.search.SearchInMoviesUseCase
import com.example.apiapp.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject



@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val searchInMoviesUseCase: SearchInMoviesUseCase
) : ViewModel() {

    var searchState: MutableStateFlow<PagingData<Results>> =
        MutableStateFlow(PagingData.empty())



    fun getSearchItem(query: String) {
        viewModelScope.launch {
            searchInMoviesUseCase.invoke(query).distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    searchState.value = it
                }
        }
    }
}