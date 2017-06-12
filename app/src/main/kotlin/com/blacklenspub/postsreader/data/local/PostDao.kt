package com.blacklenspub.postsreader.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.blacklenspub.postsreader.data.entity.Post
import io.reactivex.Flowable

@Dao
interface PostDao {

    // room doens't know how to map cursor to Observable, but you can use Flowable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdatePosts(vararg posts: Post)

    @Query("SELECT * FROM post")
    fun getAllPosts(): Flowable<List<Post>>

    // kotlin rename id to arg0
    @Query("SELECT * FROM post WHERE id = :arg0")
    fun getPostById(id: String): Flowable<Post>
}

