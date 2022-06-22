package com.cyriltheandroid.leboncv.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cyriltheandroid.leboncv.room.entity.WorkEntity
import com.cyriltheandroid.leboncv.room.converter.DateConverter
import com.cyriltheandroid.leboncv.room.dao.ArticleDAO
import com.cyriltheandroid.leboncv.room.dao.MessageDAO
import com.cyriltheandroid.leboncv.room.dao.ProfileDAO
import com.cyriltheandroid.leboncv.room.entity.*

@Database(
    entities = [
        ArticleEntity::class,
        ArticleImageEntity::class,
        LocationEntity::class,
        WorkEntity::class,
        DiplomaEntity::class,
        DurationEntity::class,
        SkillEntity::class,
        UserLocationEntity::class,
        ProfileEntity::class,
        ChatMessageEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDAO(): ArticleDAO
    abstract fun profileDAO(): ProfileDAO
    abstract fun messageDAO(): MessageDAO
}