package com.loc.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.loc.newsapp.domain.usecases.AppEntryUseCases

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    suspend fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.OnBoardingCompleted -> {
                appEntryUseCases.saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }

    }
}