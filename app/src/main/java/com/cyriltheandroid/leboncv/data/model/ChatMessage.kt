package com.cyriltheandroid.leboncv.data.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import java.util.*

data class ChatMessage(
    val sender: String? = null,
    val receiver: String? = null,
    val contentMessage: String? = null,
    val description: String? = null,
    var isChecked: Boolean = false,
    val sendingDate: Date,
    val messageType: MessageType
) : BaseObservable() {

    constructor(
        sender: String?,
        receiver: String?,
        contentMessage: String?,
        description: String?,
        messageType: MessageType
    ) : this(sender, receiver, contentMessage, description, false, Date(), messageType)

    var _isChecked: Boolean
        @Bindable get() = isChecked
        set(value) {
            isChecked = value
            notifyPropertyChanged(BR._isChecked)
        }
}