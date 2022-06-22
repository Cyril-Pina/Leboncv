package com.cyriltheandroid.leboncv.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Profile(
    private val firstName: String?,
    private val lastName: String?,
    val age: Int?,
    val pictureResId: Int?,
    val jobDescription: String?,
    val resume: String?,
    val location: Location?,
    val contact: Contact?
) : Parcelable {
    fun getFullName() = "$firstName $lastName"
}