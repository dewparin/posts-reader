package com.blacklenspub.postsreader.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.blacklenspub.postsreader.data.entity.Post
import io.reactivex.Observable

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdatePosts(posts: List<Post>): Observable<List<String>>

    @Query("SELECT * FROM post")
    fun getAllPosts(): Observable<List<Post>>

    @Query("SELECT * FROM post WHERE id = :id")
    fun getPostById(id: String): Observable<Post>
}

