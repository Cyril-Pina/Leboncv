package com.cyriltheandroid.leboncv.di

import com.cyriltheandroid.leboncv.data.mapper.ChatMessageMapper
import com.cyriltheandroid.leboncv.data.repository.ChatMessageRepository
import com.cyriltheandroid.leboncv.data.repository.ChatMessageRepositoryImpl
import com.cyriltheandroid.leboncv.room.dao.MessageDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ChatMessageModule {
    @Provides
    fun provideChatMessageRepository(
        messageDAO: MessageDAO
    ): ChatMessageRepository = ChatMessageRepositoryImpl(messageDAO)

    @Provides
    fun provideChatMessageMapper(): ChatMessageMapper = ChatMessageMapper()
}