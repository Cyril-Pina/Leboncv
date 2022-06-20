package com.cyriltheandroid.leboncv.mapper

import com.cyriltheandroid.leboncv.model.ChatMessage
import com.cyriltheandroid.leboncv.room.entity.ChatMessageEntity

class ChatMessageMapper : DataMapper<List<ChatMessage>, List<ChatMessageEntity>> {

    override fun map(obj: List<ChatMessageEntity>) =
        obj.map { chatMessageEntity ->
            ChatMessage(
                chatMessageEntity.sender,
                chatMessageEntity.receiver,
                chatMessageEntity.contentMessage,
                chatMessageEntity.description,
                false,
                chatMessageEntity.sendingDate,
                chatMessageEntity.messageType
            )
        }
}
