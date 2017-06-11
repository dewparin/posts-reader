package com.blacklenspub.postsreader.data

import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.data.local.AppDatabase
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostRepositoryImpl : PostRepository {

    // TODO : inject all sources

    private val remoteSource by lazy { retrofit.create(PostsReaderApi::class.java) }
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private val localSource by lazy { AppDatabase.instance }

    override fun addNewPost(post: Post) {
        localSource.postDao().insertOrUpdatePosts(post)
    }

    override fun getAllPosts(): Flowable<List<Post>> {
        // TODO : get remote posts
        return localSource.postDao().getAllPosts()
    }

    override fun getPostById(id: String): Flowable<Post> {
        TODO("NOT IMPLEMENTED")
    }
}