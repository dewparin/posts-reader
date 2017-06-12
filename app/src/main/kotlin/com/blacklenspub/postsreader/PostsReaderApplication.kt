package com.blacklenspub.postsreader

import android.app.Application
import com.blacklenspub.postsreader.data.di.DataModule
import com.blacklenspub.postsreader.data.local.AppDatabase
import com.blacklenspub.postsreader.presentation.di.DaggerPostsReaderComponent
import com.blacklenspub.postsreader.presentation.di.PostsReaderComponent

class PostsReaderApplication : Application() {

    companion object {
        lateinit var component: PostsReaderComponent
    }

    val BASE_URL = "http://jsonplaceholder.typicode.com"

    override fun onCreate() {
        super.onCreate()

        buildDependencyGraph()

        // TODO : this is ugly. refactor it asap.
        AppDatabase.init(applicationContext)
    }

    private fun buildDependencyGraph() {
        component = DaggerPostsReaderComponent.builder()
                .dataModule(DataModule(BASE_URL))
                .build()
    }
}

