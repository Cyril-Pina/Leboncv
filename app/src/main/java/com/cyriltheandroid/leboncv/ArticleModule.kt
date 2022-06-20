package com.cyriltheandroid.leboncv

import com.cyriltheandroid.leboncv.mapper.ArticleMapper
import com.cyriltheandroid.leboncv.repository.ArticleRepository
import com.cyriltheandroid.leboncv.repository.ArticleRepositoryImpl
import com.cyriltheandroid.leboncv.room.dao.ArticleDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ArticleModule {
    @Provides
    fun provideArticleRepository(
        articleDAO: ArticleDAO,
    ): ArticleRepository = ArticleRepositoryImpl(articleDAO)

    @Provides
    fun provideArticleMapper(): ArticleMapper = ArticleMapper()
}