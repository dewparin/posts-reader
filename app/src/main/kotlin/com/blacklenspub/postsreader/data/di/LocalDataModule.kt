package com.blacklenspub.postsreader.data.di

import android.arch.persistence.room.Room
import android.content.Context
import com.blacklenspub.postsreader.data.local.AppDatabase
import com.blacklenspub.postsreader.data.local.PostDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule(val context: Context) {

    private val DB_FILE_NAME = "posts-reader-db"

    @Provides @Singleton
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()

    @Provides @Singleton
    fun provideAppDatabase(): AppDatabase =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_FILE_NAME)
                    .build()
}
