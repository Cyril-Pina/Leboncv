package com.cyriltheandroid.leboncv.data.mapper

import com.cyriltheandroid.leboncv.data.model.*
import com.cyriltheandroid.leboncv.room.entity.*
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*

class ChatMessageMapperTest {

    lateinit var chatMessageMapper: ChatMessageMapper

    lateinit var chatMessageEntity: ChatMessageEntity

    @Before
    fun setUp() {
        chatMessageMapper = ChatMessageMapper()
        chatMessageEntity = ChatMessageEntity("sender", "receiver", "contentMessage", "description", Date(), MessageType.MY_MESSAGE)
    }

    @Test
    fun `map() with chat message as MY_MESSAGE`() {
        val chatMessageEntities: List<ChatMessageEntity> = listOf(chatMessageEntity)
        val mappedChatMessage: List<ChatMessage> = chatMessageMapper.map(chatMessageEntities)

        assertThat(mappedChatMessage.size).isEqualTo(1)
        assertThat(mappedChatMessage.first().messageType).isEqualTo(MessageType.MY_MESSAGE)
        assertThat(mappedChatMessage.first().receiver).isEqualTo("receiver")
    }
}