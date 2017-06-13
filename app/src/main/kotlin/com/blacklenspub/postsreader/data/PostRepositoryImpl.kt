package com.blacklenspub.postsreader.data

import android.arch.lifecycle.LiveData
import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.data.local.PostDao
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import io.reactivex.Scheduler

class PostRepositoryImpl(val localSource: PostDao, val remoteSource: PostsReaderApi, val scheduler: Scheduler) : PostRepository {

    override fun insertOrUpdate(post: Post) {
        localSource.insertOrUpdatePosts(post)
    }

    override fun getAllPosts(): LiveData<List<Post>> {
        remoteSource.getAllPosts()
                .subscribeOn(scheduler)
                .subscribe { posts, _ ->
                    posts?.let { localSource.insertOrUpdatePosts(*posts.toTypedArray()) }
                }
        return localSource.getAllPosts()
    }

    override fun getPostById(id: String): LiveData<Post> {
        remoteSource.getPostById(id)
                .subscribeOn(scheduler)
                .subscribe { post, _ ->
                    post?.let { localSource.insertOrUpdatePosts(post) }
                }
        return localSource.getPostById(id)
    }
}

