package com.cyriltheandroid.leboncv.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.cyriltheandroid.leboncv.data.model.LanguageLevel
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.data.model.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor() : ViewModel() {

    val languages = listOf(
        Language(R.drawable.img_flag_fr, "Français", LanguageLevel.NATALE),
        Language(R.drawable.img_flag_uk, "English", LanguageLevel.C1),
    )
}