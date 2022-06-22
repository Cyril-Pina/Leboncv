package com.cyriltheandroid.leboncv.data.model

import java.util.*

data class Language(
    val flagIdRes: Int,
    val name: String,
    val languageLevel: LanguageLevel
) : Observable()