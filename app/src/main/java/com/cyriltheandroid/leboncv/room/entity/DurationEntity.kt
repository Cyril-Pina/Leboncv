package com.cyriltheandroid.leboncv.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "duration_table")
class DurationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "date_begin") val dateBegin: Date?,
    @ColumnInfo(name = "date_end") val dateEnd: Date?,
    @ColumnInfo(name = "id_article") val idArticle: Long
) : Parcelable
