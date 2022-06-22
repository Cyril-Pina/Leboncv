package com.cyriltheandroid.leboncv.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.cyriltheandroid.leboncv.data.model.SkillType
import com.cyriltheandroid.leboncv.room.entity.ProfilePOJO
import com.cyriltheandroid.leboncv.room.entity.SkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDAO {
    @Transaction
    @Query("SELECT * FROM user_table WHERE id = 1")
    fun getUserProfile(): Flow<ProfilePOJO>

    @Query("SELECT * FROM skill_table WHERE skill_type = :skillType")
    fun getSkillsFromType(skillType: SkillType): List<SkillEntity>
}