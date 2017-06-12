package com.blacklenspub.postsreader.presentation.postlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import android.util.Log
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.PostRepositoryImpl
import com.blacklenspub.postsreader.data.entity.Post

class PostListViewModel : ViewModel() {

    // TODO : Use DI
    private val postRepo: PostRepository by lazy { PostRepositoryImpl() }

    fun getAllPosts(): LiveData<List<Post>> {
        // Room already create Flowable that run our task in worker thread. So, we don't have to subscribe on IO().
        return LiveDataReactiveStreams.fromPublisher(
                postRepo.getAllPosts()
                        .doOnNext {
                            Log.d("Dew", "PostListViewModel # Thread ID ${Thread.currentThread().id}")
                        }
        )
    }

//    fun addNewPost() {
//        Thread(Runnable {
//            var postCount = 0
//            do {
//                SystemClock.sleep(3000)
//                val post = Post().apply {
//                    id = SystemClock.currentThreadTimeMillis().toString()
//                    title = "title $id"
//                    body = "body $id"
//                }
//                postRepo.addNewPost(post)
//                postCount++
//            } while (true)
//        }).start()
//    }
}
