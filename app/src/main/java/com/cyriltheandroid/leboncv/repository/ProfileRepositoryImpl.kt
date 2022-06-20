package com.cyriltheandroid.leboncv.repository

import com.cyriltheandroid.leboncv.room.dao.ProfileDAO
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDAO: ProfileDAO
) : ProfileRepository {
    override fun getUserProfile() = profileDAO.getUserProfile()
}