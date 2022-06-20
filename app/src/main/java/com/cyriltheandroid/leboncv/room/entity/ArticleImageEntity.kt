package com.cyriltheandroid.leboncv.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "images_table")
data class ArticleImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val url: String,
    @ColumnInfo(name = "image_name") val imageName: String,
    @ColumnInfo(name = "id_article") val idArticle: Long
) : Parcelable