package com.blacklenspub.postsreader.data

import com.blacklenspub.postsreader.data.model.Post
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import io.reactivex.Single
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

    override fun getAllPosts(): Single<List<Post>> =
            remoteSource.getAllPosts()

    override fun getPostById(id: String): Single<Post> =
            remoteSource.getPostById(id)
}