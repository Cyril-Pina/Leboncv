package com.cyriltheandroid.leboncv.model

import kotlinx.parcelize.Parcelize

@Parcelize
class ProfessionalXPArticle(
    override val id: Long,
    override val title: String,
    override val desc: String?,
    override val content: String,
    override val images: List<ArticleImage>,
    val work: Work?,
    override val categoryType: CategoryType,
    override val location: Location?,
    override var isFav: Boolean
) : Article(id, title, desc, content, images, categoryType, location, isFav)