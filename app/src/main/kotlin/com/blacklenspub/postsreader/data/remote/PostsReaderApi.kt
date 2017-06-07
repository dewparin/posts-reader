package com.blacklenspub.postsreader.data.remote

import com.blacklenspub.postsreader.data.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsReaderApi {

    @GET("posts")
    fun getAllPosts(): Observable<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: String): Observable<Post>
}

