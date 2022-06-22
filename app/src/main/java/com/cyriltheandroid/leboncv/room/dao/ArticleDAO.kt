package com.cyriltheandroid.leboncv.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.cyriltheandroid.leboncv.room.entity.ArticlePOJO
import com.cyriltheandroid.leboncv.data.model.CategoryType
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {
    @Transaction
    @Query("SELECT DISTINCT * FROM article_table WHERE category_type = :categoryType")
    fun getArticlesOfCategory(categoryType: CategoryType): Flow<List<ArticlePOJO>>

    @Transaction
    @Query(
        "SELECT DISTINCT * FROM article_table " +
                "WHERE LOWER(title) LIKE '%' || LOWER(:text) || '%'" +
                "OR LOWER(`desc`) LIKE '%' || LOWER(:text) || '%'" +
                "OR LOWER(category_type) LIKE '%' || LOWER(:text) || '%'"
    )
    fun getArticlesOfCategory(text: String): Flow<List<ArticlePOJO>>

    @Transaction
    @Query(
        "SELECT DISTINCT * FROM article_table " +
                "WHERE (LOWER(title) LIKE '%' || LOWER(:text) || '%' " +
                "OR LOWER(`desc`) LIKE '%' || LOWER(:text) || '%' " +
                "OR LOWER(category_type) LIKE '%' || LOWER(:text) || '%') " +
                "AND category_type = :categoryType"
    )
    fun getArticlesOfCategory(text: String, categoryType: CategoryType): Flow<List<ArticlePOJO>>

    @Transaction
    @Query("SELECT * FROM article_table WHERE id = :idArticle")
    fun getArticle(idArticle: Long): Flow<ArticlePOJO>

    @Transaction
    @Query("SELECT DISTINCT * FROM article_table WHERE category_type = :categoryType AND id != :idArticle")
    fun getLinkedArticles(idArticle: Long, categoryType: CategoryType): Flow<List<ArticlePOJO>>

    @Transaction
    @Query("SELECT DISTINCT * FROM article_table WHERE favourite")
    fun getFavArticles(): Flow<List<ArticlePOJO>>

    @Query("UPDATE article_table SET favourite = :isFav WHERE id = :idArticle")
    fun updateFavArticle(idArticle: Long, isFav: Boolean)

    @Query("UPDATE article_table SET favourite = 0")
    fun deleteAllFavArticles()
}