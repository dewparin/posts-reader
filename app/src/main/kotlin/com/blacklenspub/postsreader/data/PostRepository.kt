package com.blacklenspub.postsreader.data

import android.arch.lifecycle.LiveData
import com.blacklenspub.postsreader.data.entity.Post

interface PostRepository {

    fun insertOrUpdate(post: Post)

    fun getAllPosts(): LiveData<List<Post>>

    fun getPostById(id: String): LiveData<Post>
}
