package com.cyriltheandroid.leboncv.data.model

import kotlinx.parcelize.Parcelize

@Parcelize
class HobbyArticle(
    override val id: Long,
    override val title: String,
    override val desc: String?,
    override val content: String,
    override val images: List<ArticleImage>,
    override val categoryType: CategoryType,
    override var isFav: Boolean,
) : Article(id, title, desc, content, images, categoryType, null, isFav)