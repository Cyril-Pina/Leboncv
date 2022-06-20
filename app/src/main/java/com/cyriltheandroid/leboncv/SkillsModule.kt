package com.cyriltheandroid.leboncv

import com.cyriltheandroid.leboncv.mapper.SkillsMapper
import com.cyriltheandroid.leboncv.repository.SkillsRepository
import com.cyriltheandroid.leboncv.repository.SkillsRepositoryImpl
import com.cyriltheandroid.leboncv.room.dao.ProfileDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SkillsModule {
    @Provides
    fun provideSkillsRepository(
        profileDAO: ProfileDAO
    ): SkillsRepository = SkillsRepositoryImpl(profileDAO)

    @Provides
    fun provideSkillsMapper(): SkillsMapper = SkillsMapper()
}