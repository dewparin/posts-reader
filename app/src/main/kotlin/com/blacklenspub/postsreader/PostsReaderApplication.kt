package com.blacklenspub.postsreader

import android.app.Application
import com.blacklenspub.postsreader.data.di.LocalDataModule
import com.blacklenspub.postsreader.data.di.PostRepositoryModule
import com.blacklenspub.postsreader.data.di.RemoteDataModule
import com.blacklenspub.postsreader.presentation.di.AppComponent
import com.blacklenspub.postsreader.presentation.di.DaggerAppComponent
import com.blacklenspub.postsreader.util.UtilModule

class PostsReaderApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    private val BASE_URL = "http://jsonplaceholder.typicode.com"

    override fun onCreate() {
        super.onCreate()
        buildDependencyGraph()
    }

    private fun buildDependencyGraph() {
        component = DaggerAppComponent.builder()
                .utilModule(UtilModule())
                .localDataModule(LocalDataModule(applicationContext))
                .remoteDataModule(RemoteDataModule(BASE_URL))
                .postRepositoryModule(PostRepositoryModule())
                .build()
    }
}

