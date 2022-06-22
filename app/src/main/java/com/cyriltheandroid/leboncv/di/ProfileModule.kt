package com.cyriltheandroid.leboncv.di

import com.cyriltheandroid.leboncv.data.mapper.ProfileMapper
import com.cyriltheandroid.leboncv.data.repository.ProfileRepository
import com.cyriltheandroid.leboncv.data.repository.ProfileRepositoryImpl
import com.cyriltheandroid.leboncv.room.dao.ProfileDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ProfileModule {
    @Provides
    fun provideProfileRepository(
        profileDAO: ProfileDAO,
    ): ProfileRepository = ProfileRepositoryImpl(profileDAO)

    @Provides
    fun provideProfileMapper(): ProfileMapper = ProfileMapper()
}