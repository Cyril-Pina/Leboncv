package com.cyriltheandroid.leboncv.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "diploma_table")
class DiplomaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String?,
    val promotion: Int?,
    val school: String?,
    @ColumnInfo(name = "date_begin") val dateBegin: Date?,
    @ColumnInfo(name = "date_end") val dateEnd: Date?,
    @ColumnInfo(name = "id_article") val idArticle: Long,
) : Parcelable
