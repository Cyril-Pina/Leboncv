package com.cyriltheandroid.leboncv.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class Article(
    open val id: Long,
    open val title: String,
    open val desc: String?,
    open val content: String,
    open val images: List<ArticleImage>,
    open val categoryType: CategoryType?,
    open val location: Location?,
    open var isFav: Boolean = false,
) : Parcelable