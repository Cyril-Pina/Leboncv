package com.cyriltheandroid.leboncv

import android.content.Context
import androidx.room.Room
import com.cyriltheandroid.leboncv.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "leboncv_database"
    ).createFromAsset("leboncv-database.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideArticleDAO(appDatabase: AppDatabase) = appDatabase.articleDAO()

    @Singleton
    @Provides
    fun provideProfileDAO(appDatabase: AppDatabase) = appDatabase.profileDAO()

    @Singleton
    @Provides
    fun provideMessageDAO(appDatabase: AppDatabase) = appDatabase.messageDAO()
}