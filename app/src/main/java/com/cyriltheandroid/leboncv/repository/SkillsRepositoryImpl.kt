package com.cyriltheandroid.leboncv.repository

import com.cyriltheandroid.leboncv.SkillType
import com.cyriltheandroid.leboncv.room.dao.ProfileDAO
import com.cyriltheandroid.leboncv.room.entity.SkillEntity
import javax.inject.Inject

class SkillsRepositoryImpl @Inject constructor(
    private val profileDAO: ProfileDAO
) : SkillsRepository {
    override fun getSkillsFromType(skillType: SkillType): List<SkillEntity> =
        profileDAO.getSkillsFromType(skillType)
}