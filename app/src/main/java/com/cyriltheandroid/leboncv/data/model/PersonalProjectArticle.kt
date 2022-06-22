package com.cyriltheandroid.leboncv.data.model

import kotlinx.parcelize.Parcelize

@Parcelize
class PersonalProjectArticle(
    override val id: Long,
    override val title: String,
    override val desc: String?,
    override val content: String,
    override val images: List<ArticleImage>,
    val duration: PPDuration?,
    override val categoryType: CategoryType,
    override var isFav: Boolean
) : Article(id, title, desc, content, images, categoryType, null, isFav)