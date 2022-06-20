package com.cyriltheandroid.leboncv.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.model.CategoryEntity
import com.cyriltheandroid.leboncv.model.CategoryType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor() : ViewModel() {

    val categories = listOf(
        CategoryEntity(
            CategoryType.PROFESSIONAL_XP,
            R.drawable.img_professional,
            R.string.xp_professional
        ),
        CategoryEntity(
            CategoryType.FORMATION,
            R.drawable.img_diploma,
            R.string.diploma
        ),
        CategoryEntity(
            CategoryType.PERSONAL_PROJECT,
            R.drawable.img_perso_project,
            R.string.personal_project
        ),
        CategoryEntity(
            CategoryType.HOBBIES,
            R.drawable.img_manga,
            R.string.hobbies
        )
    )
}