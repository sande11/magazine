package com.loc.newsapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.BuildConfig
import retrofit2.HttpException

class NewPagingSource(
    private val newsApi: NewsApi,
    private val sources: String,
    private var totalNewsCount: Int = 0
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val apiKey = BuildConfig.NEWS_API_KEY
            Log.d("API_CALL", "page: $page, apiKey: $apiKey, sources: $sources")
            val response = newsApi.getNews(sources, apiKey, page)
            totalNewsCount += response.articles.size
            val articles = response.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalNewsCount == response.totalResults) null else page + 1
            )
        } catch (e: HttpException) {
            Log.e("API_CALL_ERROR", "HTTP ${e.code()}: ${e.message()}")
            LoadResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}