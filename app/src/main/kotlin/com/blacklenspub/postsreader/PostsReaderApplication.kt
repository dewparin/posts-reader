package com.blacklenspub.postsreader

import android.app.Application
import com.blacklenspub.postsreader.data.di.LocalSourceModule
import com.blacklenspub.postsreader.data.di.PostRepositoryModule
import com.blacklenspub.postsreader.data.di.RemoteSourceModule
import com.blacklenspub.postsreader.presentation.di.AppComponent
import com.blacklenspub.postsreader.presentation.di.DaggerAppComponent

class PostsReaderApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    val BASE_URL = "http://jsonplaceholder.typicode.com"

    override fun onCreate() {
        super.onCreate()
        buildDependencyGraph()
    }

    private fun buildDependencyGraph() {
        component = DaggerAppComponent.builder()
                .localSourceModule(LocalSourceModule(applicationContext))
                .remoteSourceModule(RemoteSourceModule(BASE_URL))
                .postRepositoryModule(PostRepositoryModule())
                .build()
    }
}

