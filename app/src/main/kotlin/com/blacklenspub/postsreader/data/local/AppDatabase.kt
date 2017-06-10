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

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            instance = instance ?: Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                    "posts-reader-db").build()
            return instance!!   // I'm super sure that instance is not null here.
        }

        fun destroyInstance() {
            instance = null
        }
    }
}

