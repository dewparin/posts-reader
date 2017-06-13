package com.blacklenspub.postsreader.data

import android.arch.lifecycle.LiveData
import android.util.Log
import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.data.local.PostDao
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import io.reactivex.schedulers.Schedulers

class PostRepositoryImpl(val localSource: PostDao, val remoteSource: PostsReaderApi) : PostRepository {

    private val TAG = PostRepositoryImpl::javaClass.name

    override fun insertOrUpdate(post: Post) {
        localSource.insertOrUpdatePosts(post)
    }

    override fun getAllPosts(): LiveData<List<Post>> {
        remoteSource.getAllPosts()
                .subscribeOn(Schedulers.io())
                .subscribe { posts, _ ->
                    posts?.let {
                        Log.d(TAG, "Got ${it.size} posts from API.")
                        localSource.insertOrUpdatePosts(*posts.toTypedArray())
                    }
                }
        return localSource.getAllPosts()
    }

    override fun getPostById(id: String): LiveData<Post> {
        remoteSource.getPostById(id)
                .subscribeOn(Schedulers.io())
                .subscribe { post, _ ->
                    post?.let {
                        Log.d(TAG, "Got post id ${it.id} from API.")
                        localSource.insertOrUpdatePosts(post)
                    }
                }
        return localSource.getPostById(id)
    }
}

