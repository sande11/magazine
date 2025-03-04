package com.loc.newsapp.presentation.nvgraph

sealed class Route(val route: String) {
    object OnBoardingScreen : Route(route = "onboarding_screen")
    object HomeScreen : Route(route = "home_screen")
    object SearchScreen : Route(route = "search_screen")
    object BookmarksScreen : Route(route = "bookmarks_screen")
    object DetailScreen : Route(route = "detail_screen")
    object AppStartNavigation : Route(route = "app_start_navigation")
    object NewsNavigation : Route(route = "news_navigation")
    object NewsNavigatorScreen : Route(route = "news_navigator_screen")
}
