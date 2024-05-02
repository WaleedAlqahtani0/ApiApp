package com.example.apiapp.presentation.navigation



import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiapp.presentation.screens.onBording.OnBoardingScreen
import com.example.apiapp.presentation.screens.onBording.OnBoardingScreenViewModel
import com.example.apiapp.presentation.screens.popular.MainScreen
import com.example.apiapp.presentation.screens.popular.PopularMovieViewModel


@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    val onBoardingScreenViewModel: OnBoardingScreenViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = onBoardingScreenViewModel.startDestination){
        composable(route = Screens.OnBoardingScreen.route){
            OnBoardingScreen(onBoardingScreenViewModel, navController)
        }
        composable(route = Screens.MainScreen.route){
            val viewModel = hiltViewModel<PopularMovieViewModel>()
            MainScreen(navController = navController, viewModel.popularMovieState)
        }
    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController){
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return){
        inclusive =true
    }
}