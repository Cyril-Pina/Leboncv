package com.cyriltheandroid.leboncv.data.repository

import com.cyriltheandroid.leboncv.room.entity.ProfilePOJO
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getUserProfile(): Flow<ProfilePOJO>
}