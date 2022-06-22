package com.cyriltheandroid.leboncv.room.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.cyriltheandroid.leboncv.room.entity.*
import kotlinx.parcelize.Parcelize

@Parcelize
open class ArticlePOJO(
    @Embedded var article: ArticleEntity,
    @Relation(parentColumn = "id", entityColumn = "id_article")
    val images: List<ArticleImageEntity>,
    @Relation(parentColumn = "id", entityColumn = "id_article")
    val location: LocationEntity?,
    @Relation(parentColumn = "id", entityColumn = "id_article")
    val work: WorkEntity?,
    @Relation(parentColumn = "id", entityColumn = "id_article")
    val diploma: DiplomaEntity?,
    @Relation(parentColumn = "id", entityColumn = "id_article")
    val duration: DurationEntity?
) : Parcelable