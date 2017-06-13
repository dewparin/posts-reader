package com.blacklenspub.postsreader.presentation.di

import com.blacklenspub.postsreader.data.di.LocalSourceModule
import com.blacklenspub.postsreader.data.di.PostRepositoryModule
import com.blacklenspub.postsreader.data.di.RemoteSourceModule
import com.blacklenspub.postsreader.presentation.postdetail.PostDetailViewModel
import com.blacklenspub.postsreader.presentation.postlist.PostListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                LocalSourceModule::class,
                RemoteSourceModule::class,

                PostRepositoryModule::class
        )
)
interface AppComponent {

    fun inject(postListViewModel: PostListViewModel)

    fun inject(postDetailViewModel: PostDetailViewModel)
}

