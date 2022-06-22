package com.cyriltheandroid.leboncv.data.model

import kotlinx.parcelize.Parcelize

@Parcelize
class FormationArticle(
    override val id: Long,
    override val title: String,
    override val desc: String?,
    override val content: String,
    override val images: List<ArticleImage>,
    val diploma: Diploma?,
    override val categoryType: CategoryType,
    override val location: Location?,
    override var isFav: Boolean,
) : Article(id, title, desc, content, images, categoryType, location, isFav)
