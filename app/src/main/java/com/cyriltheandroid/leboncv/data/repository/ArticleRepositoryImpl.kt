package com.cyriltheandroid.leboncv.data.repository

import com.cyriltheandroid.leboncv.room.entity.ArticlePOJO
import com.cyriltheandroid.leboncv.data.model.CategoryType
import com.cyriltheandroid.leboncv.room.dao.ArticleDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleDAO: ArticleDAO
) : ArticleRepository {

    override fun getArticlesOfCategory(categoryType: CategoryType): Flow<List<ArticlePOJO>> =
        articleDAO.getArticlesOfCategory(categoryType)

    override fun getArticlesOfCategory(
        text: String,
        categoryType: CategoryType
    ): Flow<List<ArticlePOJO>> = articleDAO.getArticlesOfCategory(text, categoryType)

    override fun getArticlesOfCategory(text: String): Flow<List<ArticlePOJO>> =
        articleDAO.getArticlesOfCategory(text)

    override fun getLinkedArticles(
        idArticle: Long,
        categoryType: CategoryType
    ): Flow<List<ArticlePOJO>> =
        articleDAO.getLinkedArticles(idArticle, categoryType)

    override fun getArticle(idArticle: Long) = articleDAO.getArticle(idArticle)

    override fun updateFavArticle(idArticle: Long, isFav: Boolean) {
        articleDAO.updateFavArticle(idArticle, isFav)
    }

    override fun getFavArticles(): Flow<List<ArticlePOJO>> = articleDAO.getFavArticles()

    override fun deleteAllFavArticles() {
        articleDAO.deleteAllFavArticles()
    }
}