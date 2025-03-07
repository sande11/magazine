package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)
val pages = listOf(
    Page(
        title = "Welcome to NewsApp",
        description = "Get the latest news from around the world",
        image = R.drawable.second
    ),
    Page(
        title = "Personalized News",
        description = "Get news that matters to you",
        image = R.drawable.first
    ),
    Page(
        title = "Stay Updated",
        description = "Get news updates in real-time",
        image = R.drawable.get_started
    )
)