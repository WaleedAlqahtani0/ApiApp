package com.example.apiapp.presentation.screens.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.apiapp.contest
import com.example.apiapp.model.BackdropSize
import com.example.apiapp.model.SearchResponse
import com.example.apiapp.model.UIState


@Composable
fun MainScreen(navController: NavHostController, popularMovieState: MutableState<UIState<SearchResponse>>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        when (val result = popularMovieState.value) {
            is UIState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    items(result.data?.results.orEmpty()) {

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("${contest.MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${it.posterPath}")
                                .build(), contentDescription = "",
                            Modifier.padding(10.dp)
                        )
                        Text(
                            text = it.title.orEmpty(),
                            Modifier.padding(start = 10.dp)
                        )

                    }
                }
            }

            is UIState.Empty -> {
                println("Empty")
            }
            is UIState.Error -> {
                error("Error")
            }
            is UIState.Loading -> {
                println("Loading")

            }
        }
    }
}