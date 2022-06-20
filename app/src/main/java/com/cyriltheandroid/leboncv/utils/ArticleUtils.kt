package com.cyriltheandroid.leboncv.utils

import android.view.View
import com.cyriltheandroid.leboncv.model.*

class ArticleUtils {
    companion object {
        @JvmStatic
        fun getArticleTitleFromType(article: Article, categoryType: CategoryType) =
            when (categoryType) {
                CategoryType.PROFESSIONAL_XP -> {
                    val professionalArticle = article as ProfessionalXPArticle
                    professionalArticle.work?.companyName
                }
                CategoryType.PERSONAL_PROJECT -> {
                    val personalProjArticle = article as PersonalProjectArticle
                    personalProjArticle.title
                }
                CategoryType.HOBBIES -> {
                    val hobbyArticle = article as HobbyArticle
                    hobbyArticle.title
                }
                CategoryType.FORMATION -> {
                    val formationArticle = article as FormationArticle
                    formationArticle.diploma?.school
                }
                else -> ""
            }

        @JvmStatic
        fun getArticleDescFromType(article: Article, categoryType: CategoryType) =
            when (categoryType) {
                CategoryType.PROFESSIONAL_XP -> {
                    val professionalArticle = article as ProfessionalXPArticle
                    professionalArticle.work?.position
                }
                CategoryType.PERSONAL_PROJECT -> {
                    val personalProjArticle = article as PersonalProjectArticle
                    personalProjArticle.desc
                }
                CategoryType.HOBBIES -> {
                    val hobbyArticle = article as HobbyArticle
                    hobbyArticle.desc
                }
                CategoryType.FORMATION -> {
                    val formationArticle = article as FormationArticle
                    formationArticle.diploma?.title
                }
                else -> ""
            }

        @JvmStatic
        fun getArticleFirstSubtitle(article: Article, categoryType: CategoryType): String? =
            when (categoryType) {
                CategoryType.PROFESSIONAL_XP -> {
                    val professionalXPArticle = article as ProfessionalXPArticle
                    "${professionalXPArticle.work?.contractType?.getValue()} • ${professionalXPArticle.location?.getCityWithZipCode()}"
                }
                CategoryType.PERSONAL_PROJECT -> {
                    val personalProjectArticle = article as PersonalProjectArticle
                    personalProjectArticle.desc
                }
                CategoryType.HOBBIES -> {
                    val hobbyArticle = article as HobbyArticle
                    hobbyArticle.desc
                }
                CategoryType.FORMATION -> {
                    val formationArticle = article as FormationArticle
                    "${formationArticle.diploma?.title} • ${article.diploma?.promotion}"
                }
                else -> ""
            }

        @JvmStatic
        fun secondSubtitleVisibility(categoryType: CategoryType): Int =
            when (categoryType) {
                CategoryType.HOBBIES -> View.GONE
                else -> View.VISIBLE
            }

        @JvmStatic
        fun getArticleSecondSubtitle(article: Article, categoryType: CategoryType): String =
            when (categoryType) {
                CategoryType.PROFESSIONAL_XP -> {
                    val professionalXPArticle = article as ProfessionalXPArticle
                    "${professionalXPArticle.work?.getReadableBeginDate()} " +
                            "- ${professionalXPArticle.work?.getReadableEndingDate() ?: "Aujourd'hui"}"
                }
                CategoryType.PERSONAL_PROJECT -> {
                    val personalProjectArticle = article as PersonalProjectArticle
                    "${personalProjectArticle.duration?.getReadableBeginDate()} " +
                            "- ${personalProjectArticle.duration?.getReadableEndingDate() ?: "Aujourd'hui"}"
                }
                CategoryType.FORMATION -> {
                    val formationArticle = article as FormationArticle
                    "${formationArticle.diploma?.getReadableBeginDate()} " +
                            "- ${formationArticle.diploma?.getReadableEndingDate() ?: "Aujourd'hui"}"
                }
                else -> ""
            }

        @JvmStatic
        fun getFavArticleFirstSubtitle(article: Article, categoryType: CategoryType): String =
            when (categoryType) {
                CategoryType.PROFESSIONAL_XP -> {
                    val professionalXPArticle = article as ProfessionalXPArticle
                    "${professionalXPArticle.location?.getCityWithZipCode()}"
                }
                CategoryType.PERSONAL_PROJECT -> {
                    val personalProjectArticle = article as PersonalProjectArticle
                    "${personalProjectArticle.duration?.getReadableBeginDate()} " +
                            "- ${personalProjectArticle.duration?.getReadableEndingDate() ?: "Aujourd'hui"}"
                }
                CategoryType.FORMATION -> {
                    val formationArticle = article as FormationArticle
                    "${formationArticle.location?.getCityWithZipCode()}"
                }
                else -> ""
            }

        @JvmStatic
        fun getFavArticleSecondSubtitle(article: Article, categoryType: CategoryType): String =
            when (categoryType) {
                CategoryType.PROFESSIONAL_XP -> {
                    val professionalXPArticle = article as ProfessionalXPArticle
                    "${professionalXPArticle.work?.getDateDifference()}"
                }
                CategoryType.PERSONAL_PROJECT -> {
                    val personalProjectArticle = article as PersonalProjectArticle
                    "${personalProjectArticle.duration?.getDateDifference()}"
                }
                CategoryType.HOBBIES -> {
                    val hobbyArticle = article as HobbyArticle
                    hobbyArticle.content
                }
                CategoryType.FORMATION -> {
                    val formationArticle = article as FormationArticle
                    "${formationArticle.diploma?.getDateDifference()}"
                }
                else -> ""
            }
    }
}