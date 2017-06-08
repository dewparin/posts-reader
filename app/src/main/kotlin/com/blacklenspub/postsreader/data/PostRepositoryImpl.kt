package com.blacklenspub.postsreader.data

import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostRepositoryImpl : PostRepository {

    // TODO : integrate local source

    private val remoteSource by lazy {
        retrofit.create(PostsReaderApi::class.java)
    }
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    override fun getAllPosts() = remoteSource.getAllPosts()

    override fun getPostById(id: String) = remoteSource.getPostById(id)
}