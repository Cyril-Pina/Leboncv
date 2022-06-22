package com.cyriltheandroid.leboncv.data.repository

import com.cyriltheandroid.leboncv.room.dao.MessageDAO
import com.cyriltheandroid.leboncv.room.entity.ChatMessageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatMessageRepositoryImpl @Inject constructor(
    private val messageDAO: MessageDAO
) : ChatMessageRepository {

    override fun getChatMessages(): Flow<List<ChatMessageEntity>> = messageDAO.getChatMessages()

    override fun addNewChatMessage(chatMessage: ChatMessageEntity) {
        messageDAO.addNewMessage(chatMessage)
    }
}