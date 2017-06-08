package com.blacklenspub.postsreader.data

import com.blacklenspub.postsreader.data.model.Post
import io.reactivex.Single

interface PostRepository {

    fun getAllPosts(): Single<List<Post>>

    fun getPostById(id: String): Single<Post>
}
