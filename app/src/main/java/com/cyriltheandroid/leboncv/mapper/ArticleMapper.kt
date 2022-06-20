package com.cyriltheandroid.leboncv.mapper

import com.cyriltheandroid.leboncv.model.*
import com.cyriltheandroid.leboncv.room.entity.ArticleImageEntity
import com.cyriltheandroid.leboncv.room.entity.DiplomaEntity
import com.cyriltheandroid.leboncv.room.entity.DurationEntity
import com.cyriltheandroid.leboncv.room.entity.LocationEntity
import com.cyriltheandroid.leboncv.utils.LAT_INDEX
import com.cyriltheandroid.leboncv.utils.LNG_INDEX
import com.google.android.gms.maps.model.LatLng

class ArticleMapper : DataMapper<List<Article>, List<ArticlePOJO>> {

    override fun map(obj: List<ArticlePOJO>): List<Article> {
        val articles = obj.map { articlePOJO ->
            when (articlePOJO.article.categoryType) {
                CategoryType.PROFESSIONAL_XP ->
                    ProfessionalXPArticle(
                        articlePOJO.article.id,
                        articlePOJO.article.title,
                        articlePOJO.article.desc,
                        articlePOJO.article.content,
                        imagesEntityToArticleImages(articlePOJO.images),
                        if (articlePOJO.work != null) workEntityToWork(articlePOJO.work) else null,
                        articlePOJO.article.categoryType!!,
                        if (articlePOJO.location != null) locationEntityToLocation(articlePOJO.location) else null,
                        articlePOJO.article.isFav
                    )
                CategoryType.PERSONAL_PROJECT ->
                    PersonalProjectArticle(
                        articlePOJO.article.id,
                        articlePOJO.article.title,
                        articlePOJO.article.desc,
                        articlePOJO.article.content,
                        imagesEntityToArticleImages(articlePOJO.images),
                        if (articlePOJO.duration != null) durationEntityToPPDuration(articlePOJO.duration) else null,
                        articlePOJO.article.categoryType!!,
                        articlePOJO.article.isFav
                    )
                CategoryType.HOBBIES ->
                    HobbyArticle(
                        articlePOJO.article.id,
                        articlePOJO.article.title,
                        articlePOJO.article.desc,
                        articlePOJO.article.content,
                        imagesEntityToArticleImages(articlePOJO.images),
                        articlePOJO.article.categoryType!!,
                        articlePOJO.article.isFav
                    )
                CategoryType.FORMATION ->
                    FormationArticle(
                        articlePOJO.article.id,
                        articlePOJO.article.title,
                        articlePOJO.article.desc,
                        articlePOJO.article.content,
                        imagesEntityToArticleImages(articlePOJO.images),
                        if (articlePOJO.diploma != null) diplomaEntityToDiploma(articlePOJO.diploma) else null,
                        articlePOJO.article.categoryType!!,
                        if (articlePOJO.location != null) locationEntityToLocation(articlePOJO.location) else null,
                        articlePOJO.article.isFav
                    )
                else ->
                    Article(
                        articlePOJO.article.id,
                        articlePOJO.article.title,
                        articlePOJO.article.desc,
                        articlePOJO.article.content,
                        imagesEntityToArticleImages(articlePOJO.images),
                        articlePOJO.article.categoryType,
                        if (articlePOJO.location != null) locationEntityToLocation(articlePOJO.location) else null,
                        articlePOJO.article.isFav
                    )
            }
        }
        return articles
    }

    private fun imagesEntityToArticleImages(imageEntities: List<ArticleImageEntity>): List<ArticleImage> {
        return imageEntities.map {
            ArticleImage(it.url, it.imageName)
        }
    }

    private fun workEntityToWork(workEntity: WorkEntity): Work {
        return Work(
            workEntity.position,
            workEntity.companyName,
            workEntity.contractType,
            workEntity.dateBegin,
            workEntity.dateEnd
        )
    }

    private fun diplomaEntityToDiploma(diplomaEntity: DiplomaEntity): Diploma {
        return Diploma(
            diplomaEntity.title,
            diplomaEntity.promotion,
            diplomaEntity.school,
            diplomaEntity.dateBegin,
            diplomaEntity.dateEnd
        )
    }

    private fun durationEntityToPPDuration(durationEntity: DurationEntity): PPDuration {
        return PPDuration(durationEntity.dateBegin, durationEntity.dateEnd)
    }

    private fun locationEntityToLocation(locationEntity: LocationEntity): Location {
        val latLngArray = locationEntity.latLng?.split(",")
        val lat = latLngArray?.get(LAT_INDEX)?.toDouble()
        val lng = latLngArray?.get(LNG_INDEX)?.toDouble()
        return Location(
            locationEntity.streetAddress,
            locationEntity.city,
            locationEntity.zipCode,
            locationEntity.country,
            if (lat != null && lng != null) LatLng(lat, lng) else null
        )
    }
}