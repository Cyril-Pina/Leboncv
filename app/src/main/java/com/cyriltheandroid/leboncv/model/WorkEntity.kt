package com.cyriltheandroid.leboncv.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "work_table")
class WorkEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val position: String?,
    @ColumnInfo(name = "company_name") val companyName: String?,
    @ColumnInfo(name = "contract_type") val contractType: ContractType?,
    @ColumnInfo(name = "date_begin") val dateBegin: Date?,
    @ColumnInfo(name = "date_end") val dateEnd: Date?,
    @ColumnInfo(name = "id_article") val idArticle: Long
) : Parcelable