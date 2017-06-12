package com.blacklenspub.postsreader.presentation.postlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.entity.Post
import javax.inject.Inject

class PostListViewModel : ViewModel() {

    @Inject
    lateinit var postRepo: PostRepository

    fun getAllPosts(): LiveData<List<Post>> = postRepo.getAllPosts()

    // TODO : remove this code
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
