package com.blacklenspub.postsreader.data.di

import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.PostRepositoryImpl
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule(val baseUrl: String) {

    @Provides @Singleton
    fun providePostRepository(remoteSource: PostsReaderApi): PostRepository
            = PostRepositoryImpl(remoteSource)

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

    // TODO : provide local source
}

