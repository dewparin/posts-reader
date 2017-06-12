package com.blacklenspub.postsreader.data

import android.util.Log
import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.data.local.AppDatabase
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
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

    private val localSource by lazy { AppDatabase.instance.postDao() }

    override fun addNewPost(post: Post) {
        localSource.insertOrUpdatePosts(post)
    }

    override fun getAllPosts(): Flowable<List<Post>> {
        remoteSource.getAllPosts()
                .subscribeOn(Schedulers.io())
                .subscribe { posts ->
                    Log.d("Dew", "PostRepositoryImpl # Got Posts from API. Thread ID ${Thread.currentThread().id}")
                    localSource.insertOrUpdatePosts(*posts.toTypedArray())
                }
        return localSource.getAllPosts()
    }

    override fun getPostById(id: String): Flowable<Post> {
        remoteSource.getPostById(id)
                .subscribeOn(Schedulers.io())
                .subscribe { post ->
                    Log.d("Dew", "PostRepositoryImpl # Got post by id from API. Thread ID ${Thread.currentThread().id}")
                    localSource.insertOrUpdatePosts(post)
                }
        return localSource.getPostById(id)
    }
}