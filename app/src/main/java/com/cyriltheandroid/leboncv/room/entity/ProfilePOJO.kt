package com.cyriltheandroid.leboncv.room.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.cyriltheandroid.leboncv.room.entity.ProfileEntity
import com.cyriltheandroid.leboncv.room.entity.UserLocationEntity
import kotlinx.parcelize.Parcelize

@Parcelize
open class ProfilePOJO(
    @Embedded var profileEntity: ProfileEntity,
    @Relation(parentColumn = "id", entityColumn = "id_user")
    val location: UserLocationEntity,
) : Parcelable