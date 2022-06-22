package com.cyriltheandroid.leboncv.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.ui.adapter.ChatMessageAdapter
import com.cyriltheandroid.leboncv.databinding.FragmentMessageBinding
import com.cyriltheandroid.leboncv.data.model.ChatMessage
import com.cyriltheandroid.leboncv.data.model.MessageType
import com.cyriltheandroid.leboncv.ui.viewmodel.ChatMessageViewModel
import com.cyriltheandroid.leboncv.utils.FIRST_INDEX
import dagger.hilt.android.AndroidEntryPoint
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import javax.inject.Inject

const val DESC_FIRST_MSG = "Automessage - Firstname"
const val DESC_SECOND_MSG = "Automessage - Contact"

@AndroidEntryPoint
class MessageFragment : Fragment() {

    private val chatMessageViewModel: ChatMessageViewModel by activityViewModels()
    private lateinit var binding: FragmentMessageBinding

    private var userName: String? = null
    private var isFirstMessage: Boolean = false

    @Inject
    lateinit var chatMessageAdapter: ChatMessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initClickListeners()
    }

    private fun initObservers() {
        chatMessageViewModel.chatMessages.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                initMessagesRecyclerView(it)
                if (isFirstMessage && userName != null) {
                    sendSecondChatBotMessage()
                    isFirstMessage = false
                }
            } else {
                isFirstMessage = true
                sendFirstChatBotMessages()
            }
        }
    }

    private fun sendFirstChatBotMessages() {
        sendNewMessage(
            getString(R.string.first_msg_chat_bot),
            DESC_FIRST_MSG,
            MessageType.OTHER_MESSAGE
        )
    }

    private fun sendSecondChatBotMessage() {
        sendNewMessage(
            getString(R.string.second_msg_chat_bot, userName),
            DESC_SECOND_MSG,
            MessageType.OTHER_MESSAGE
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initMessagesRecyclerView(messages: List<ChatMessage>) {
        chatMessageAdapter.chatMessages = messages
        if (binding.messagesRecyclerView.adapter == null) {
            chatMessageAdapter.msgClickListener.onItemClick = { _, obj ->
                val chatMessage = obj as ChatMessage
                chatMessageViewModel.updateMessageDataVisibility(chatMessage)
                binding.messagesRecyclerView.scrollTo(0, 0)
            }
            chatMessageAdapter.backgroundClickListener.onItemClick = { _, _ ->
                hideKeyboard()
            }
            binding.messagesRecyclerView.adapter = chatMessageAdapter
        } else {
            binding.messagesRecyclerView.adapter!!.notifyDataSetChanged()
            scrollRecyclerViewOnNewMessage()
        }
    }

    private fun sendNewMessage(
        contentMessage: String,
        description: String? = null,
        messageType: MessageType
    ) {
        val sender =
            if (messageType == MessageType.MY_MESSAGE) userName else getString(R.string.dev_first_name)
        val receiver =
            if (messageType == MessageType.MY_MESSAGE) getString(R.string.dev_first_name) else userName
        chatMessageViewModel.addNewChatMessage(
            ChatMessage(
                sender,
                receiver,
                contentMessage,
                description,
                messageType
            )
        )
    }

    private fun initClickListeners() {
        hideKeyboardOnBackgroundClick()
        sendMessageOnClick()
        scrollDownOnEditTextFocused()
    }

    private fun scrollDownOnEditTextFocused() {
        KeyboardVisibilityEvent.setEventListener(
            activity
        ) {
            if (it) {
                scrollRecyclerViewOnNewMessage()
            }
        }
    }

    private fun hideKeyboardOnBackgroundClick() {
        binding.root.setOnClickListener {
            hideKeyboard()
        }
        binding.headerMessagesView.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun sendMessageOnClick() {
        binding.sendMsgImageButton.setOnClickListener {
            val messageEditText = binding.messageEditText
            if (!isMessageEditTextEmpty(messageEditText)) {
                saveUserName(messageEditText.text.toString())
                sendNewMessage(
                    messageEditText.text.toString(),
                    messageType = MessageType.MY_MESSAGE
                )
                clearMessageEditText(messageEditText)
            }
        }
    }

    private fun saveUserName(contentMessage: String) {
        if (isFirstMessage) {
            userName = contentMessage.trim()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
        view?.requestFocus()
    }

    private fun isMessageEditTextEmpty(messageEditText: EditText): Boolean {
        return messageEditText.text.toString().trim().isEmpty()
    }

    private fun clearMessageEditText(messageEditText: EditText) {
        messageEditText.text.clear()
    }

    private fun scrollRecyclerViewOnNewMessage() {
        binding.messagesRecyclerView.smoothScrollToPosition(FIRST_INDEX)
    }
}