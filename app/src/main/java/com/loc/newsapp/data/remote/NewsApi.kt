package com.loc.newsapp.data.remote

import com.loc.newsapp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: String,
        @Query("apikey") apiKey: String,
        page1: Int
    ): NewsResponse
}