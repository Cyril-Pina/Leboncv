package com.cyriltheandroid.leboncv.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cyriltheandroid.leboncv.data.mapper.ChatMessageMapper
import com.cyriltheandroid.leboncv.data.model.ChatMessage
import com.cyriltheandroid.leboncv.data.model.MessageType
import com.cyriltheandroid.leboncv.data.repository.ChatMessageRepository
import com.cyriltheandroid.leboncv.room.entity.ChatMessageEntity
import com.cyriltheandroid.leboncv.utils.ONE_HOUR
import com.cyriltheandroid.leboncv.utils.asEntity
import com.cyriltheandroid.leboncv.utils.getDatesDifferenceInHour
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatMessageViewModel @Inject constructor(
    private val chatMessageRepository: ChatMessageRepository,
    private val chatMessageMapper: ChatMessageMapper
) : ViewModel() {
    val chatMessages: LiveData<List<ChatMessage>> = chatMessageRepository.getChatMessages()
        .map {
            chatMessageMapper.map(it)
        }.asLiveData()

    fun addNewChatMessage(chatMessage: ChatMessage) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isPreviousMessageOld(chatMessage.sendingDate)) {
                addTimestampMessage()
            }
            chatMessageRepository.addNewChatMessage(chatMessage.asEntity())
        }
    }

    private fun addTimestampMessage() {
        chatMessageRepository.addNewChatMessage(
            ChatMessageEntity(
                null, null, null, null, Date(), MessageType.TIMESTAMP
            )
        )
    }

    fun updateMessageDataVisibility(chatMessage: ChatMessage) {
        chatMessage._isChecked = !chatMessage._isChecked
    }

    private fun isPreviousMessageOld(msgDate: Date): Boolean {
        chatMessages.value?.let {
            if (it.isEmpty()) {
                return true
            }
            val previousMessage = it.first()
            return previousMessage.messageType != MessageType.TIMESTAMP
                    && getDatesDifferenceInHour(previousMessage.sendingDate, msgDate) >= ONE_HOUR
        }
        return false
    }
}