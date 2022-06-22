package com.cyriltheandroid.leboncv.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.data.model.Category
import com.cyriltheandroid.leboncv.data.model.CategoryType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor() : ViewModel() {

    val categories = listOf(
        Category(
            CategoryType.PROFESSIONAL_XP,
            R.drawable.img_professional,
            R.string.xp_professional
        ),
        Category(
            CategoryType.FORMATION,
            R.drawable.img_diploma,
            R.string.diploma
        ),
        Category(
            CategoryType.PERSONAL_PROJECT,
            R.drawable.img_perso_project,
            R.string.personal_project
        ),
        Category(
            CategoryType.HOBBIES,
            R.drawable.img_manga,
            R.string.hobbies
        )
    )
}