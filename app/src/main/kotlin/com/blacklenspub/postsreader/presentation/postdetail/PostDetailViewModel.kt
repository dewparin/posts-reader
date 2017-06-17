package com.blacklenspub.postsreader.presentation.postdetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.entity.Post
import javax.inject.Inject

class PostDetailViewModel : ViewModel() {

    @Inject
    lateinit var postRepo: PostRepository

    private var post: LiveData<Post>? = null

    fun getPostDetail(postId: String): LiveData<Post> {
        // This is a simple way to cache data. You could cache it in db instead.
        post = post ?: postRepo.getPostById(postId)
        return post!!   // Don't worry I'm a good Engineer ;-)
    }

    // This function is for demonstration purpose only.
    fun updatePost(post: Post) {
        postRepo.insertOrUpdate(post)
    }
}

