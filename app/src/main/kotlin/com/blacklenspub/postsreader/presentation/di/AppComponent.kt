package com.blacklenspub.postsreader.presentation.di

import com.blacklenspub.postsreader.data.di.LocalDataModule
import com.blacklenspub.postsreader.data.di.PostRepositoryModule
import com.blacklenspub.postsreader.data.di.RemoteDataModule
import com.blacklenspub.postsreader.presentation.postdetail.PostDetailViewModel
import com.blacklenspub.postsreader.presentation.postlist.PostListViewModel
import com.blacklenspub.postsreader.util.Logger
import com.blacklenspub.postsreader.util.UtilModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                UtilModule::class,

                LocalDataModule::class,
                RemoteDataModule::class,

                PostRepositoryModule::class
        )
)
interface AppComponent {

    val logger: Logger

    fun inject(postListViewModel: PostListViewModel)

    fun inject(postDetailViewModel: PostDetailViewModel)
}

