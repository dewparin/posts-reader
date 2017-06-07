package com.blacklenspub.postsreader.data

import com.blacklenspub.postsreader.data.model.Post
import io.reactivex.Observable

interface PostDataSource {

    fun getAllPosts(): Observable<List<Post>>

    fun getPostById(id: String): Observable<Post>
}
