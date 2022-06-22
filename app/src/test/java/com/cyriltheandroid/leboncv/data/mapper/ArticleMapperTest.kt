package com.cyriltheandroid.leboncv.data.mapper

import com.cyriltheandroid.leboncv.data.model.Article
import com.cyriltheandroid.leboncv.data.model.CategoryType
import com.cyriltheandroid.leboncv.data.model.ContractType
import com.cyriltheandroid.leboncv.data.model.ProfessionalXPArticle
import com.cyriltheandroid.leboncv.room.entity.*
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*

class ArticleMapperTest {

    lateinit var articleMapper: ArticleMapper

    lateinit var articleEntity: ArticleEntity
    lateinit var professionalXPArticle: ArticleEntity
    lateinit var images: List<ArticleImageEntity>
    lateinit var location: LocationEntity
    lateinit var work: WorkEntity
    lateinit var diploma: DiplomaEntity
    lateinit var duration: DurationEntity

    @Before
    fun setUp() {
        articleMapper = ArticleMapper()

        articleEntity = ArticleEntity(1, "title", "desc", "content",
            CategoryType.NO_CATEGORY, "url", false)
        professionalXPArticle = ArticleEntity(1, "title", "desc", "content",
        CategoryType.PROFESSIONAL_XP, "url", false)
        images = listOf(ArticleImageEntity(1, "url", "imageName", 1))
        location = LocationEntity(1, "address", "city", "zipcode", "country", "4.349394,2.894933", 1)
        work = WorkEntity(1, "position", "companyName", ContractType.PERMANENT, Date(), Date(), 1)
        diploma = DiplomaEntity(1, "title", 1, "school", Date(), Date(), 1)
        duration = DurationEntity(1, Date(), Date(), 1)
    }

    @Test
    fun `map() with article as NO_CATEGORY`() {
        val articlesPOJO: List<ArticlePOJO> = listOf(ArticlePOJO(articleEntity, images, location, work, diploma, duration))
        val mappedArticle: List<Article> = articleMapper.map(articlesPOJO)

        assertThat(mappedArticle.size).isEqualTo(1)
        assertThat(mappedArticle.first().categoryType).isEqualTo(CategoryType.NO_CATEGORY)
        assertThat(mappedArticle.first().images.first().imageName).isEqualTo("imageName")
        assertThat(mappedArticle.first().location?.latLng).isNotNull()
    }

    @Test
    fun `map() with article as PROFESSIONAL_XP`() {
        val articlesPOJO: List<ArticlePOJO> = listOf(ArticlePOJO(professionalXPArticle, images, location, work, diploma, duration))
        val mappedArticle: List<Article> = articleMapper.map(articlesPOJO)
        val firstProfessionalXPArticle = mappedArticle.first() as ProfessionalXPArticle

        assertThat(firstProfessionalXPArticle.categoryType).isEqualTo(CategoryType.PROFESSIONAL_XP)
        assertThat(firstProfessionalXPArticle.work?.companyName).isEqualTo("companyName")
        assertThat(firstProfessionalXPArticle.title).isEqualTo("title")
    }
}