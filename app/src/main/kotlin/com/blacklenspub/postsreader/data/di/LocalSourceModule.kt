package com.blacklenspub.postsreader.data.di

import android.content.Context
import com.blacklenspub.postsreader.data.local.AppDatabase
import com.blacklenspub.postsreader.data.local.PostDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalSourceModule(val context: Context) {

    @Provides @Singleton
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()

    @Provides @Singleton
    fun provideAppDatabase(): AppDatabase {
        AppDatabase.init(context)
        return AppDatabase.instance
    }
}

