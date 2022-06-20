package com.cyriltheandroid.leboncv.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cyriltheandroid.leboncv.room.entity.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDAO {
    @Query("SELECT * FROM message_table ORDER BY id DESC")
    fun getChatMessages(): Flow<List<ChatMessageEntity>>

    @Insert
    fun addNewMessage(message: ChatMessageEntity)
}