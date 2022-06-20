package com.cyriltheandroid.leboncv

import com.cyriltheandroid.leboncv.mapper.ProfileMapper
import com.cyriltheandroid.leboncv.repository.ProfileRepository
import com.cyriltheandroid.leboncv.repository.ProfileRepositoryImpl
import com.cyriltheandroid.leboncv.room.dao.ProfileDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ProfileModule {
    @Provides
    fun provideProfileRepository(
        profileDAO: ProfileDAO,
    ): ProfileRepository = ProfileRepositoryImpl(profileDAO)

    @Provides
    fun provideProfileMapper(): ProfileMapper = ProfileMapper()
}