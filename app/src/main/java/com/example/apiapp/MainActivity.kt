package com.example.apiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apiapp.contest.MOVIE_IMAGE_BASE_URL
import com.example.apiapp.model.BackdropSize
import com.example.apiapp.model.UIState
import com.example.apiapp.presentation.screens.popular.PopularMovieViewModel
import com.example.apiapp.ui.theme.ApiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiAppTheme {
                val viewModel by viewModels<PopularMovieViewModel>()
                when( val result = viewModel.popularMovieState.value){
                    is UIState.Success -> {
                        Surface {
                            LazyColumn(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer)) {
                                items(result.data?.results.orEmpty()) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        GlideImage(
                                            model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}${it.posterPath}",
                                            contentDescription = "",
                                            modifier = Modifier.padding(12.dp).size(150.dp),
                                        )
                                        Text(
                                            text = it.title.orEmpty(),
                                            modifier = Modifier.padding(12.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    is UIState.Empty -> {
                        Text(text = "Empty")
                    }
                    is UIState.Error ->{
                        Text(text = result.error)
                    }
                    is UIState.Loading -> {
                        Text(text = "Loding")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiAppTheme {
        Greeting("Android")
    }
}
