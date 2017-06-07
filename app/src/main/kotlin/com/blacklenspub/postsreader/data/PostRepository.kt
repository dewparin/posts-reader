package com.blacklenspub.postsreader.data

import com.blacklenspub.postsreader.data.model.Post
import io.reactivex.Observable

interface PostRepository {

    fun getAllPosts(): Observable<List<Post>>

    fun getPostById(id: String): Observable<Post>
}
