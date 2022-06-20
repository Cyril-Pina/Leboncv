package com.cyriltheandroid.leboncv.model

import android.os.Parcelable
import com.cyriltheandroid.leboncv.SkillType
import kotlinx.parcelize.Parcelize

@Parcelize
class Skill(val skill: String?, val description: String?, val skillType: SkillType?) : Parcelable