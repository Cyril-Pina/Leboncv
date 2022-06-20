package com.cyriltheandroid.leboncv.mapper

import com.cyriltheandroid.leboncv.model.Skill
import com.cyriltheandroid.leboncv.room.entity.SkillEntity

class SkillsMapper : DataMapper<List<Skill>, List<SkillEntity>> {

    override fun map(obj: List<SkillEntity>): List<Skill> {
        return obj.map {
            Skill(it.skill, it.description, it.skillType)
        }
    }
}