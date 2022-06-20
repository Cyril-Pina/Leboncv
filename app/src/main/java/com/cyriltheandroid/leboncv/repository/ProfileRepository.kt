package com.cyriltheandroid.leboncv.repository

import com.cyriltheandroid.leboncv.model.ProfilePOJO
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getUserProfile(): Flow<ProfilePOJO>
}