package com.cyriltheandroid.leboncv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyriltheandroid.leboncv.databinding.ItemIncomingMessageBinding
import com.cyriltheandroid.leboncv.databinding.ItemOutgoingMessageBinding
import com.cyriltheandroid.leboncv.databinding.ItemTimestampMessageBinding
import com.cyriltheandroid.leboncv.model.ChatMessage
import com.cyriltheandroid.leboncv.model.MessageType
import javax.inject.Inject

class ChatMessageAdapter @Inject constructor(
    val msgClickListener: ItemClickListener,
    val backgroundClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var chatMessages: List<ChatMessage> = emptyList()

    class TimestampViewHolder(val binding: ItemTimestampMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatMessage: ChatMessage, backgroundClickListener: ItemClickListener) {
            binding.chatMessage = chatMessage
            binding.backgroundClickListener = backgroundClickListener
        }
    }

    class MyMsgViewHolder(val binding: ItemOutgoingMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            chatMessage: ChatMessage,
            clickListener: ItemClickListener,
            backgroundClickListener: ItemClickListener
        ) {
            binding.chatMessage = chatMessage
            binding.msgClickListener = clickListener
            binding.backgroundClickListener = backgroundClickListener
        }
    }

    class OtherMsgViewHolder(val binding: ItemIncomingMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            chatMessage: ChatMessage,
            msgClickListener: ItemClickListener,
            backgroundClickListener: ItemClickListener
        ) {
            binding.chatMessage = chatMessage
            binding.msgClickListener = msgClickListener
            binding.backgroundClickListener = backgroundClickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            MessageType.TIMESTAMP.ordinal -> {
                val binding = ItemTimestampMessageBinding.inflate(layoutInflater, parent, false)
                TimestampViewHolder(binding)
            }
            MessageType.MY_MESSAGE.ordinal -> {
                val binding = ItemOutgoingMessageBinding.inflate(layoutInflater, parent, false)
                MyMsgViewHolder(binding)
            }
            MessageType.OTHER_MESSAGE.ordinal -> {
                val binding = ItemIncomingMessageBinding.inflate(layoutInflater, parent, false)
                OtherMsgViewHolder(binding)
            }
            else -> {
                val binding = ItemTimestampMessageBinding.inflate(layoutInflater, parent, false)
                TimestampViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MessageType.TIMESTAMP.ordinal -> {
                val timestampViewHolder = holder as TimestampViewHolder
                timestampViewHolder.bind(
                    chatMessages[position],
                    backgroundClickListener
                )
            }
            MessageType.MY_MESSAGE.ordinal -> {
                val myMsgViewHolder = holder as MyMsgViewHolder
                myMsgViewHolder.bind(
                    chatMessages[position],
                    msgClickListener,
                    backgroundClickListener
                )
            }
            MessageType.OTHER_MESSAGE.ordinal -> {
                val otherMsgViewHolder = holder as OtherMsgViewHolder
                otherMsgViewHolder.bind(
                    chatMessages[position],
                    msgClickListener,
                    backgroundClickListener
                )
            }
        }
    }

    override fun getItemCount(): Int = chatMessages.size

    override fun getItemViewType(position: Int): Int {
        return chatMessages[position].messageType.ordinal
    }
}