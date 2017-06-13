package com.blacklenspub.postsreader.presentation.postlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.entity.Post
import javax.inject.Inject

class PostListViewModel : ViewModel() {

    @Inject
    lateinit var postRepo: PostRepository

    private var posts: LiveData<List<Post>>? = null

    fun getAllPosts(): LiveData<List<Post>> {
        posts = posts ?: postRepo.getAllPosts()
        return posts!!  // Trust me I'm an Engineer ;-)
    }
}
