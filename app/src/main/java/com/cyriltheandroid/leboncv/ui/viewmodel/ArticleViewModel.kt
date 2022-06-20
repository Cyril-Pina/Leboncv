package com.cyriltheandroid.leboncv.ui.viewmodel

import android.view.View
import androidx.lifecycle.*
import com.airbnb.lottie.LottieAnimationView
import com.cyriltheandroid.leboncv.mapper.ArticleMapper
import com.cyriltheandroid.leboncv.model.Article
import com.cyriltheandroid.leboncv.model.CategoryType
import com.cyriltheandroid.leboncv.repository.ArticleRepository
import com.cyriltheandroid.leboncv.utils.startFavouriteAnimationView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val articleMapper: ArticleMapper,
    private val state: SavedStateHandle
) : ViewModel() {
    private val articleLiveData = MutableLiveData<Article>()
    private val allArticlesLiveData = MutableLiveData<List<List<Article>>>()
    private val articlesLiveData = MutableLiveData<List<Article>>()

    var article: LiveData<Article> = articleLiveData
    val allArticles: LiveData<List<List<Article>>> = allArticlesLiveData
    val articles: LiveData<List<Article>> = articlesLiveData
    val favArticles: LiveData<List<Article>> = articleRepository.getFavArticles()
        .map {
            articleMapper.map(it)
        }
        .asLiveData()

    var categoryType: CategoryType? = null

    init {
        viewModelScope.launch {
            if (state.contains("article")) {
                val article = state.get<Article>("article")!!
                articleLiveData.value = article
                article.categoryType?.let { getLinkedArticles(article.id, it) }
            } else if (state.contains("categoryType")) {
                categoryType = state.get<CategoryType>("categoryType")!!
                getArticlesOfCategory("")
            }
        }
    }

    fun getAllArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val professionalXPArticles =
                async {
                    articleRepository.getArticlesOfCategory(CategoryType.PROFESSIONAL_XP)
                        .map {
                            articleMapper.map(it)
                        }.first()
                }
            val formationArticles =
                async {
                    articleRepository.getArticlesOfCategory(CategoryType.FORMATION)
                        .map {
                            articleMapper.map(it)
                        }.first()
                }
            val personalProjectArticles =
                async {
                    articleRepository.getArticlesOfCategory(CategoryType.PERSONAL_PROJECT)
                        .map {
                            articleMapper.map(it)
                        }.first()
                }
            val hobbyArticles =
                async {
                    articleRepository.getArticlesOfCategory(CategoryType.HOBBIES)
                        .map {
                            articleMapper.map(it)
                        }.first()
                }
            allArticlesLiveData.postValue(
                listOf(
                    professionalXPArticles.await(),
                    formationArticles.await(),
                    personalProjectArticles.await(),
                    hobbyArticles.await()
                )
            )
        }
    }

    fun getArticlesOfCategory(text: String) {
        viewModelScope.launch {
            this@ArticleViewModel.articlesLiveData.value = if (isCategoryTypeNull()) {
                articleRepository.getArticlesOfCategory(text)
            } else {
                articleRepository.getArticlesOfCategory(text, categoryType!!)
            }.map {
                articleMapper.map(it)
            }.first()
        }
    }

    private fun getLinkedArticles(idArticle: Long, categoryType: CategoryType) {
        viewModelScope.launch(Dispatchers.IO) {
            articlesLiveData.postValue(
                articleRepository.getLinkedArticles(idArticle, categoryType).map {
                    articleMapper.map(it)
                }.first()
            )
        }
    }

    fun updateFavArticle(idArticle: Long, isFav: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            articleRepository.updateFavArticle(idArticle, isFav)
        }
    }

    fun updateFavArticle(article: Article, animationView: View) {
        startFavouriteAnimationView(article.isFav, animationView as LottieAnimationView)
        article.isFav = !article.isFav
        viewModelScope.launch(Dispatchers.IO) {
            articleRepository.updateFavArticle(article.id, article.isFav)
        }
    }

    fun deleteAllFavArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            articleRepository.deleteAllFavArticles()
        }
    }

    private fun isCategoryTypeNull() =
        categoryType == null || (categoryType != null && categoryType == CategoryType.NO_CATEGORY)
}