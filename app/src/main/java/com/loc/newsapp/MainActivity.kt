package com.loc.newsapp

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen
import com.loc.newsapp.ui.theme.NewsAppTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { true }

        Handler(mainLooper).postDelayed({
            setContent {
                NewsAppTheme {
                    splashScreen.setKeepOnScreenCondition { false }
                    OnBoardingScreen()
                }
            }
        }, 3000)
    }
}
