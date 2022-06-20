package com.cyriltheandroid.leboncv.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleImage(
    val url: String,
    val imageName: String,
) : Parcelable