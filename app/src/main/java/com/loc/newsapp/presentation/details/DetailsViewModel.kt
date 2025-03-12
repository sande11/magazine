package com.loc.newsapp.presentation.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    var sideEffect = mutableStateOf<String?>(null)

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article == null) {
                        upsertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect -> {
                sideEffect.value = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        sideEffect.value = "Article deleted"
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article = article)
        sideEffect.value = "Article saved"
    }
}