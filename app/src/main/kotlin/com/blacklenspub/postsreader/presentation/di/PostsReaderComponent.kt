package com.blacklenspub.postsreader.presentation.di

import com.blacklenspub.postsreader.data.di.DataModule
import com.blacklenspub.postsreader.presentation.postdetail.PostDetailViewModel
import com.blacklenspub.postsreader.presentation.postlist.PostListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DataModule::class))
interface PostsReaderComponent {

    fun inject(postListViewModel: PostListViewModel)

    fun inject(postDetailViewModel: PostDetailViewModel)
}

