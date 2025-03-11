package com.loc.newsapp.presentation.details

import com.loc.newsapp.domain.model.Article

open class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article): DetailsEvent()

    object RemoveSideEffect: DetailsEvent()
}