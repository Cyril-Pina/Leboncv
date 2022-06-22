package com.cyriltheandroid.leboncv.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Skill(val skill: String?, val description: String?, val skillType: SkillType?) : Parcelable