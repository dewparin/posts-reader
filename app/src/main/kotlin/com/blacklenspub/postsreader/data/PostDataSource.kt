package com.blacklenspub.postsreader.data

import android.arch.lifecycle.LiveData
import com.blacklenspub.postsreader.data.model.Post

interface PostDataSource {

    fun getAllPosts(): LiveData<List<Post>>

    fun getPostById(id: String): LiveData<Post>
}
