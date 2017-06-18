package com.blacklenspub.postsreader.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.blacklenspub.postsreader.data.entity.Post

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}

