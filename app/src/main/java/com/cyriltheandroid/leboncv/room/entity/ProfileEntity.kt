package com.cyriltheandroid.leboncv.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
class ProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    val age: Int?,
    @ColumnInfo(name = "picture_id") val pictureResId: Int?,
    @ColumnInfo(name = "picture_url") val pictureUrl: String?,
    @ColumnInfo(name = "job_description") val jobDescription: String?,
    @ColumnInfo(name = "life_resume") val lifeResume: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?,
    @ColumnInfo(name = "country_prefix") val countryPrefix: Int?,
    @ColumnInfo(name = "email_address") val emailAddress: String?,
) : Parcelable
