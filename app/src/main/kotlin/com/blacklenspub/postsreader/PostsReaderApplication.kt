package com.blacklenspub.postsreader

import android.app.Application
import com.blacklenspub.postsreader.data.local.AppDatabase

class PostsReaderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // TODO : this is ugly. refactor it asap.
        AppDatabase.init(applicationContext)
    }
}

