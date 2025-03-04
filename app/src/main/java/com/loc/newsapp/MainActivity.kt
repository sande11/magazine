package com.loc.newsapp

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen
import com.loc.newsapp.ui.theme.NewsAppTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.loc.newsapp.presentation.nvgraph.NavGraph
import com.loc.newsapp.presentation.onboarding.OnBoardingViewModel
import com.loc.newsapp.presentation.onboarding.OnBoardingEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val splashScreen = installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }

        splashScreen.setKeepOnScreenCondition { true }

        Handler(mainLooper).postDelayed({
            setContent {
                NewsAppTheme {

                    val isSystemInDarkTheme = isSystemInDarkTheme()
                    val systemController = rememberSystemUiController()

                    SideEffect {
                        systemController.setSystemBarsColor(
                            color = Color.Transparent,
                            darkIcons = !isSystemInDarkTheme
                        )
                    }

                    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                        val startDestination = viewModel.startDestination
                        NavGraph(startDestination = startDestination)
                        splashScreen.setKeepOnScreenCondition { false }
                        val viewModel: OnBoardingViewModel = hiltViewModel()
                        OnBoardingScreen(
                            event = { event: OnBoardingEvent -> lifecycleScope.launch { viewModel.onEvent(event) } }
                        )
                    }
                }
            }
        }, 3000)
    }
}