package com.blacklenspub.postsreader

import android.app.Application
import com.blacklenspub.postsreader.data.di.DataModule
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
    }

    private fun buildDependencyGraph() {
        component = DaggerPostsReaderComponent.builder()
                .dataModule(DataModule(applicationContext, BASE_URL))
                .build()
    }
}

