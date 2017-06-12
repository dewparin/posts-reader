package com.blacklenspub.postsreader.presentation.postdetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.PostRepositoryImpl
import com.blacklenspub.postsreader.data.entity.Post

class PostDetailViewModel : ViewModel() {

    // TODO : Use DI
    private val postRepo: PostRepository by lazy { PostRepositoryImpl() }

    fun getPostDetail(postId: String): LiveData<Post> = postRepo.getPostById(postId)
}

