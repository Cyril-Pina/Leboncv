package com.cyriltheandroid.leboncv.di

import com.cyriltheandroid.leboncv.data.mapper.ArticleMapper
import com.cyriltheandroid.leboncv.data.repository.ArticleRepository
import com.cyriltheandroid.leboncv.data.repository.ArticleRepositoryImpl
import com.cyriltheandroid.leboncv.room.dao.ArticleDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ArticleModule {
    @Provides
    fun provideArticleRepository(
        articleDAO: ArticleDAO,
    ): ArticleRepository = ArticleRepositoryImpl(articleDAO)

    @Provides
    fun provideArticleMapper(): ArticleMapper = ArticleMapper()
}