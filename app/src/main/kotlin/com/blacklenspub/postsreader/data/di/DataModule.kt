package com.blacklenspub.postsreader.data.di

import android.content.Context
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.PostRepositoryImpl
import com.blacklenspub.postsreader.data.local.AppDatabase
import com.blacklenspub.postsreader.data.local.PostDao
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule(val context: Context, val baseUrl: String) {

    @Provides @Singleton
    fun providePostRepository(localSource: PostDao, remoteSource: PostsReaderApi): PostRepository
            = PostRepositoryImpl(localSource, remoteSource)

    @Provides @Singleton
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()

    @Provides @Singleton
    fun provideAppDatabase(): AppDatabase {
        AppDatabase.init(context)
        return AppDatabase.instance
    }

    @Provides @Singleton
    fun providePostsReaderApi(retrofit: Retrofit): PostsReaderApi
            = retrofit.create(PostsReaderApi::class.java)

    @Provides @Singleton
    fun provideRetrofit(): Retrofit
            = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

