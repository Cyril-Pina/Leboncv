package com.cyriltheandroid.leboncv.data.model

import com.cyriltheandroid.leboncv.R

enum class CategoryType {
    PROFESSIONAL_XP {
        override fun getValueResId() = R.string.xp_professional
    },
    PERSONAL_PROJECT {
        override fun getValueResId() = R.string.personal_project
    },
    HOBBIES {
        override fun getValueResId() = R.string.hobbies
    },
    FORMATION {
        override fun getValueResId() = R.string.diploma
    },
    NO_CATEGORY {
        override fun getValueResId() = R.string.no_category
    };

    abstract fun getValueResId(): Int
}