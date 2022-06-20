package com.cyriltheandroid.leboncv.repository

import com.cyriltheandroid.leboncv.model.ArticlePOJO
import com.cyriltheandroid.leboncv.model.CategoryType
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getArticlesOfCategory(categoryType: CategoryType): Flow<List<ArticlePOJO>>
    fun getArticlesOfCategory(text: String, categoryType: CategoryType): Flow<List<ArticlePOJO>>
    fun getArticlesOfCategory(text: String): Flow<List<ArticlePOJO>>
    fun getLinkedArticles(idArticle: Long, categoryType: CategoryType): Flow<List<ArticlePOJO>>
    fun getArticle(idArticle: Long): Flow<ArticlePOJO>
    fun getFavArticles(): Flow<List<ArticlePOJO>>
    fun updateFavArticle(idArticle: Long, isFav: Boolean)
    fun deleteAllFavArticles()
}