package com.loc.newsapp

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen
import com.loc.newsapp.ui.theme.NewsAppTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { true }

        Handler(mainLooper).postDelayed({
            setContent {
                NewsAppTheme {
                    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                        splashScreen.setKeepOnScreenCondition { false }
                        OnBoardingScreen()
                    }
                }
            }
        }, 3000)
    }
}
