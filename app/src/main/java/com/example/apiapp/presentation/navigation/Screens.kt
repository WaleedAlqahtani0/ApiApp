package com.example.apiapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screens(val route: String) {
    data object OnBoarding : Screens("on_boarding")
    data object Home : Screens("popular_movie_screen")
    data object Search : Screens("search_route")
    data object Profile : Screens("profile_route")
    data object MovieDetail : Screens("movie_detail_route")
}


data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = Screens.Search.route
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Filled.AccountCircle,
                route = Screens.Profile.route
            ),
        )
    }
}