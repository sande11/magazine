package com.loc.newsapp.presentation.nvgraph

import android.net.Uri
import com.google.gson.Gson
import com.loc.newsapp.domain.model.Article

sealed class Route(val route: String) {
    object OnBoardingScreen : Route(route = "onboarding_screen")
    object HomeScreen : Route(route = "home_screen")
    object SearchScreen : Route(route = "search_screen")
    object BookmarksScreen : Route(route = "bookmarks_screen")
    object DetailScreen {
        const val route = "detail_screen"

        fun createRoute(article: Article): String {
            val articleJson = Uri.encode(Gson().toJson(article))
            return "$route/$articleJson"
        }
    }
    object AppStartNavigation : Route(route = "app_start_navigation")
    object NewsNavigation : Route(route = "news_navigation")
    object NewsNavigatorScreen : Route(route = "news_navigator_screen")
}
