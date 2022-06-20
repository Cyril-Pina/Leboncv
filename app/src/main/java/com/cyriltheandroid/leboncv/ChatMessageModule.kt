package com.cyriltheandroid.leboncv

import com.cyriltheandroid.leboncv.mapper.ChatMessageMapper
import com.cyriltheandroid.leboncv.repository.ChatMessageRepository
import com.cyriltheandroid.leboncv.repository.ChatMessageRepositoryImpl
import com.cyriltheandroid.leboncv.room.dao.MessageDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ChatMessageModule {
    @Provides
    fun provideChatMessageRepository(
        messageDAO: MessageDAO
    ): ChatMessageRepository = ChatMessageRepositoryImpl(messageDAO)

    @Provides
    fun provideChatMessageMapper(): ChatMessageMapper = ChatMessageMapper()
}