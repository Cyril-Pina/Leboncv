package com.cyriltheandroid.leboncv.data.mapper

import com.cyriltheandroid.leboncv.data.model.Skill
import com.cyriltheandroid.leboncv.data.model.SkillType
import com.cyriltheandroid.leboncv.room.entity.SkillEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class SkillsMapperTest {

    lateinit var skillsMapper: SkillsMapper

    lateinit var skillEntity: SkillEntity

    @Before
    fun setUp() {
        skillsMapper = SkillsMapper()
        skillEntity = SkillEntity(1, "skill", "description", SkillType.SOFT_SKILL)
    }

    @Test
    fun mapTest() {
        val skillEntities: List<SkillEntity> = listOf(skillEntity)
        val mappedSkills: List<Skill> = skillsMapper.map(skillEntities)

        assertThat(mappedSkills.first().skill).isEqualTo("skill")
        assertThat(mappedSkills.first().skillType).isEqualTo(SkillType.SOFT_SKILL)
    }
}