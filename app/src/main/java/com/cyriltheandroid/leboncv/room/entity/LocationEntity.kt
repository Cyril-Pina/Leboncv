package com.cyriltheandroid.leboncv.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "location_table")
class LocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "street_address") val streetAddress: String?,
    val city: String?,
    @ColumnInfo(name = "zip_code") val zipCode: String?,
    val country: String?,
    @ColumnInfo(name = "lat_lng") val latLng: String?,
    @ColumnInfo(name = "id_article") val idArticle: Long
) : Parcelable