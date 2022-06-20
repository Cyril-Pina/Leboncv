package com.cyriltheandroid.leboncv.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Contact(
    private val phoneNumber: String?,
    private val prefixNumber: Int?,
    val emailAddress: String?
) :
    Parcelable {
    constructor() : this(null, null, null)

    fun getFullNumberFormat() = "+$prefixNumber ${phoneNumber?.substring(1)}"
}