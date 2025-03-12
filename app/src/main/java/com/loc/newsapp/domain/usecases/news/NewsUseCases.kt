package com.loc.newsapp.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNews,
    val upsertArticle: UpsetArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle: SelectArticle
)
