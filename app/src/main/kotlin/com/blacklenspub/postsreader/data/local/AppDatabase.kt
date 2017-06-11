package com.blacklenspub.postsreader.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.blacklenspub.postsreader.data.entity.Post

// AppDatabase needs to be declared as abstract class.
// Thus, we can't use `object` to make a singleton here.
@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {

        private val DB_FILE_NAME = "posts-reader-db"

        lateinit var instance: AppDatabase

        fun init(context: Context): AppDatabase {
//            instance = Room.databaseBuilder(context.applicationContext,
            instance = Room.inMemoryDatabaseBuilder(context.applicationContext,
                    AppDatabase::class.java)
//                    DB_FILE_NAME)
                    .build()
            return instance
        }
    }
}

