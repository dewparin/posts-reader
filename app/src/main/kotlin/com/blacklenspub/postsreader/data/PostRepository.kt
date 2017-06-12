package com.blacklenspub.postsreader.data

import com.blacklenspub.postsreader.data.entity.Post
import io.reactivex.Flowable

interface PostRepository {

    fun insertOrUpdate(post: Post)

    fun getAllPosts(): Flowable<List<Post>>

    fun getPostById(id: String): Flowable<Post>
}
