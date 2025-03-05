package com.loc.newsapp.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.loc.newsapp.presentation.common.NewsButton
import com.loc.newsapp.presentation.common.NewsTextButton
import com.loc.newsapp.presentation.nvgraph.Route
import com.loc.newsapp.presentation.onboarding.components.OnBoardingPage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Job,
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val scope = rememberCoroutineScope()

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Next")
                pages.lastIndex -> listOf("Back", "Get Started")
                else -> listOf("Back", "Next")
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            OnBoardingPage(page = pages[index])
        }

        // Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 40.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (buttonState.value[0].isNotEmpty()) {
                NewsTextButton(
                    text = buttonState.value[0],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                )
            }
            NewsButton(
                text = buttonState.value[1],
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage < pages.lastIndex) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            event(OnBoardingEvent.OnBoardingCompleted)
                            navController.navigate(Route.NewsNavigatorScreen.route) {
                                popUpTo(Route.OnBoardingScreen.route) { inclusive = true }
                            }
                        }
                    }
                }
            )
        }
    }
}

