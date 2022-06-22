package com.cyriltheandroid.leboncv.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyriltheandroid.leboncv.data.model.MessageType
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "message_table")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val sender: String? = null,
    val receiver: String? = null,
    @ColumnInfo(name = "content_message") val contentMessage: String? = null,
    val description: String? = null,
    @ColumnInfo(name = "sending_date") val sendingDate: Date,
    @ColumnInfo(name = "message_type") val messageType: MessageType
) : Parcelable {
    constructor(
        sender: String?,
        receiver: String?,
        contentMessage: String?,
        description: String?,
        sendingDate: Date,
        messageType: MessageType
    ) : this(0, sender, receiver, contentMessage, description, sendingDate, messageType)
}