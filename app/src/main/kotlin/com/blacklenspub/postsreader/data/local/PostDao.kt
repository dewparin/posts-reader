package com.blacklenspub.postsreader.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.blacklenspub.postsreader.data.entity.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdatePosts(vararg posts: Post)

    @Query("SELECT * FROM post")
    fun getAllPosts(): LiveData<List<Post>>

    // id is changed to arg0 in generated code
    @Query("SELECT * FROM post WHERE id = :arg0")
    fun getPostById(id: String): LiveData<Post>
}

