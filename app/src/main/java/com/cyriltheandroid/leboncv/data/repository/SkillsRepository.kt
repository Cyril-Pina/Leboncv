package com.cyriltheandroid.leboncv.data.repository

import com.cyriltheandroid.leboncv.data.model.SkillType
import com.cyriltheandroid.leboncv.room.entity.SkillEntity

interface SkillsRepository {
    fun getSkillsFromType(skillType: SkillType): List<SkillEntity>
}