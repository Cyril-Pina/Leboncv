package com.cyriltheandroid.leboncv.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyriltheandroid.leboncv.data.model.SkillType
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "skill_table")
class SkillEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val skill: String?,
    val description: String?,
    @ColumnInfo(name = "skill_type") val skillType: SkillType?
) : Parcelable