package com.loc.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun upsert(article: Article)

    @Delete
    abstract suspend fun delete(article: Article)

    @Query("SELECT * FROM Article ")
    abstract fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url=:url")
    abstract suspend fun getArticle(url: String): Article?

}