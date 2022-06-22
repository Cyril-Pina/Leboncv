package com.cyriltheandroid.leboncv.di

import com.cyriltheandroid.leboncv.data.mapper.SkillsMapper
import com.cyriltheandroid.leboncv.data.repository.SkillsRepository
import com.cyriltheandroid.leboncv.data.repository.SkillsRepositoryImpl
import com.cyriltheandroid.leboncv.room.dao.ProfileDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SkillsModule {
    @Provides
    fun provideSkillsRepository(
        profileDAO: ProfileDAO
    ): SkillsRepository = SkillsRepositoryImpl(profileDAO)

    @Provides
    fun provideSkillsMapper(): SkillsMapper = SkillsMapper()
}