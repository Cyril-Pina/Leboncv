package com.cyriltheandroid.leboncv.utils

import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.data.model.ChatMessage
import com.cyriltheandroid.leboncv.data.model.Contact
import com.cyriltheandroid.leboncv.data.model.ItemContact
import com.cyriltheandroid.leboncv.room.entity.ChatMessageEntity
import com.google.android.gms.maps.GoogleMap

fun Fragment.setCloseFragmentOnClick(backButton: ImageButton) {
    backButton.setOnClickListener {
        val navController = findNavController()
        navController.navigateUp()
    }
}

fun Contact.getItemsContact(): List<ItemContact> {
    return listOf(
        ItemContact(
            R.drawable.ic_mail,
            emailAddress ?: "",
            R.string.send_email
        ),
        ItemContact(
            R.drawable.ic_smartphone,
            getFullNumberFormat(),
            R.string.phone_call
        ),
    )
}

fun ChatMessage.asEntity() = ChatMessageEntity(
    sender, receiver, contentMessage, description, sendingDate, messageType
)

fun LottieAnimationView.startAnimation(minFrame: Int, maxFrame: Int) {
    setMinAndMaxFrame(minFrame, maxFrame)
    playAnimation()
}

fun GoogleMap.disableGesture() {
    this.uiSettings.setAllGesturesEnabled(false)
}

fun RecyclerView.addDivider() {
    val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
    addItemDecoration(dividerItemDecoration)
}