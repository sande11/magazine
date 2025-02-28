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
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Personalized News",
        description = "Get news that matters to you",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Stay Updated",
        description = "Get news updates in real-time",
        image = R.drawable.onboarding3
    )
)