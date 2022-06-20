package com.cyriltheandroid.leboncv.repository

import com.cyriltheandroid.leboncv.room.entity.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

interface ChatMessageRepository {
    fun getChatMessages(): Flow<List<ChatMessageEntity>>
    fun addNewChatMessage(chatMessage: ChatMessageEntity)
}