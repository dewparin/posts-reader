package com.blacklenspub.postsreader.presentation.postlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.util.Logger
import javax.inject.Inject

class PostListViewModel : ViewModel() {

    private val TAG = "show_time"

    @Inject lateinit var logger: Logger
    @Inject lateinit var postRepo: PostRepository

    private var counter = 0
    private var posts: LiveData<List<Post>>? = null

    fun getAllPosts(): LiveData<List<Post>> {
        // this is for demonstration purpose only
        counter++
        logger.logDebug(TAG, "Counter: $counter")

        // This is a simple way to cache data. You could cache it in db instead.
        posts = posts ?: postRepo.getAllPosts()
        return posts!!  // Trust me I'm an Engineer ;-)
    }
}
