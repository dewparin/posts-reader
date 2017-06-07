package com.blacklenspub.postsreader.presentation.postlist

import android.arch.lifecycle.ViewModel
import com.blacklenspub.postsreader.data.PostDataSource
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.model.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostListViewModel : ViewModel() {

    // TODO : Use DI
    private val postRepo: PostDataSource by lazy { PostRepository() }

    fun getAllPosts(): Observable<List<Post>> =
            postRepo.getAllPosts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}

