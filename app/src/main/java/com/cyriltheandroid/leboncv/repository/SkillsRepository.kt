package com.cyriltheandroid.leboncv.repository

import com.cyriltheandroid.leboncv.SkillType
import com.cyriltheandroid.leboncv.room.entity.SkillEntity

interface SkillsRepository {
    fun getSkillsFromType(skillType: SkillType): List<SkillEntity>
}