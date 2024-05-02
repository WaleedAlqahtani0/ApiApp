package com.example.apiapp.presentation.screens.onBording

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.apiapp.R
import com.example.apiapp.presentation.navigation.Screens
import com.example.apiapp.presentation.navigation.popUpToTop

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(onBoardingScreenViewModel: OnBoardingScreenViewModel, navController: NavHostController) {

    val images = listOf(
        R.drawable.movie_night_amico,
        R.drawable.home_cinema_amico,
        R.drawable.horror_movie_amico
    )

    val titles = listOf(
        "Welcome to CineSpectra!",
        "Quick and Easy Booking",
        "Tailored Just for You"
    )

    val descriptions = listOf(
        "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
        "Reserve your favorite seat in just a few steps. No waiting, no hassle!",
        "Personalize your experience! With movie recommendations and exclusive offers, enjoy the cinema your way."
    )

    val onBoardingCompleted by onBoardingScreenViewModel.onBoardingCompleted.collectAsState()


    if (onBoardingCompleted) {
        navController.navigate(Screens.MainScreen.route){
            popUpToTop(navController)
        }
    } else {
        val pagerState = rememberPagerState(
            pageCount = { 3 }
        )


        Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {
            Box(
                Modifier
                    .rotate(270f)
                    .height(50.dp)
                    .fillMaxWidth()
                    .paint(
                        painter = painterResource(id = R.drawable.brutalist_10),
                        contentScale = ContentScale.FillWidth
                    )
                    .align(Alignment.BottomCenter)
            ) {
            }
            Column(
                Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PageIndicator(3, pagerState.currentPage, Modifier.padding(30.dp))
                HorizontalPager(state = pagerState, Modifier.wrapContentSize()) { currentPage ->
                    Column(
                        Modifier
                            .wrapContentSize()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = images[currentPage]),
                            contentDescription = "",
                            Modifier.size(400.dp)
                        )
                        Text(
                            text = titles[currentPage], textAlign = TextAlign.Center,
                            fontSize = 25.sp, fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = descriptions[currentPage], textAlign = TextAlign.Center,
                            fontSize = 20.sp, color = Color.White
                        )
                    }
                }

            }
            if (pagerState.currentPage == 2) {
                Button(
                    onClick = { navController.navigate(Screens.MainScreen.route){
                        popUpToTop(navController)
                    }
                        onBoardingScreenViewModel.saveOnBoardingState(true)},
                    Modifier
                        .padding(bottom = 40.dp)
                        .align(Alignment.BottomCenter)
                        .width(400.dp)
                        .height(80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF374951)
                    ),
                    shape = RoundedCornerShape(10)
                ) {
                    Text(
                        text = "Let's get started",
                        fontSize = 20.sp
                    )

                }

            }
        }
    }
}


@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier){
    Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier){
        repeat(pageCount){
            IndicatorSingleDot(isSelected = it == currentPage)
        }
    }

}
@Composable
fun IndicatorSingleDot(isSelected: Boolean){
    val width = animateDpAsState(targetValue = if(isSelected) 35.dp else 15.dp, label = "")
    Box(modifier = Modifier
        .padding(2.dp)
        .height(15.dp)
        .width(width.value)
        .clip(CircleShape)
        .background(if (isSelected) Color(0xFFF6D776) else Color.Gray))
}