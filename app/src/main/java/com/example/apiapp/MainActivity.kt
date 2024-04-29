package com.example.apiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apiapp.model.UIState
import com.example.apiapp.presentation.screens.popular.PopularMovieViewModel
import com.example.apiapp.ui.theme.ApiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiAppTheme {
                val viewModel by viewModels<PopularMovieViewModel>()
                when( val result = viewModel.popularMovieState.value){
                    is UIState.Success -> {
                        Surface {
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(result.data?.results.orEmpty()) {
                                    Text(
                                        text = it.title.orEmpty(),
                                        modifier = Modifier.padding(12.dp)
                                    )
                                }

                            }
                        }
                    }
                    is UIState.Empty -> {
                        Text(text = "Empty")
                    }
                    is UIState.Error ->{
                        Text(text = "Erorr")
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