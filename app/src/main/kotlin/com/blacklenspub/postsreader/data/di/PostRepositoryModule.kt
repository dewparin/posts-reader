package com.blacklenspub.postsreader.data.di

import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.PostRepositoryImpl
import com.blacklenspub.postsreader.data.local.PostDao
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PostRepositoryModule {

    @Provides @Singleton
    fun providePostRepository(localSource: PostDao, remoteSource: PostsReaderApi): PostRepository
            = PostRepositoryImpl(localSource, remoteSource)
}

