package com.loc.newsapp.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.gson.Gson
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.bookmark.BookmarkScreen
import com.loc.newsapp.presentation.bookmark.BookmarkViewModel
import com.loc.newsapp.presentation.details.DetailScreen
import com.loc.newsapp.presentation.details.DetailsViewModel
import com.loc.newsapp.presentation.home.HomeScreen
import com.loc.newsapp.presentation.home.HomeViewModel
import com.loc.newsapp.presentation.onboarding.OnBoardingViewModel
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen
import kotlinx.coroutines.launch

@Composable
fun NavGraph(startDestination: String, navController: NavHostController) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = { event -> viewModel.viewModelScope.launch { viewModel.onEvent(event) } },
                    navController = navController
                )
            }
        }
        composable(route = Route.DetailScreen.route + "/{article}") { backStackEntry ->
            val articleJson = backStackEntry.arguments?.getString("article")
            val article = articleJson?.let { json ->
                // Convert JSON back to an Article object
                Gson().fromJson(json, Article::class.java)
            }

            article?.let {
                val viewModel: DetailsViewModel = hiltViewModel()
                DetailScreen(
                    article = it,
                    event = { event -> viewModel.onEvent(event) },
                    navigateUp = { navController.popBackStack() }
                )
            }
        }

        // Group news-related screens
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigate = { route -> navController.navigate(route) }) { article ->
                    navController.navigate(Route.DetailScreen.createRoute(article))
                }
            }
        }
        composable(route = Route.BookmarksScreen.route) {
            val bookmarkViewModel: BookmarkViewModel = hiltViewModel()
            BookmarkScreen(
                state = bookmarkViewModel.state.value,
                navigateToDetails = { article -> navController.navigate(Route.DetailScreen.createRoute(article)) },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

