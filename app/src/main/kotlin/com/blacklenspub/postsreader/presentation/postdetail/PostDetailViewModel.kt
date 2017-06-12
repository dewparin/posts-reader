package com.blacklenspub.postsreader.presentation.postdetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.entity.Post
import javax.inject.Inject

class PostDetailViewModel : ViewModel() {

    @Inject
    lateinit var postRepo: PostRepository

    fun getPostDetail(postId: String): LiveData<Post> = postRepo.getPostById(postId)
}

