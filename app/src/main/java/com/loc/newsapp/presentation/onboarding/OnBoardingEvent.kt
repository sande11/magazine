package com.loc.newsapp.presentation.onboarding

sealed class OnBoardingEvent{
    object OnBoardingCompleted: OnBoardingEvent()
    companion object {
        val SaveAppEntry: OnBoardingEvent = OnBoardingCompleted
    }
}