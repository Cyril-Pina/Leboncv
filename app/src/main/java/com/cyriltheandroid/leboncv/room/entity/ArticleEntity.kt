package com.cyriltheandroid.leboncv.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyriltheandroid.leboncv.data.model.CategoryType
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "article_table")
open class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val desc: String?,
    val content: String,
    @ColumnInfo(name = "category_type") val categoryType: CategoryType?,
    val url: String?,
    @ColumnInfo(name = "favourite") var isFav: Boolean = false,
) : Parcelable