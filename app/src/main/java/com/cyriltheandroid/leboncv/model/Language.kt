package com.cyriltheandroid.leboncv.model

import com.cyriltheandroid.leboncv.LanguageLevel
import java.util.*

data class Language(
    val flagIdRes: Int,
    val name: String,
    val languageLevel: LanguageLevel
) : Observable()