package com.blacklenspub.postsreader.presentation.postlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.blacklenspub.postsreader.data.PostRepository
import com.blacklenspub.postsreader.data.PostRepositoryImpl
import com.blacklenspub.postsreader.data.entity.Post
import io.reactivex.schedulers.Schedulers

class PostListViewModel : ViewModel() {

    // TODO : Use DI
    private val postRepo: PostRepository by lazy { PostRepositoryImpl() }

    fun getAllPosts(): LiveData<List<Post>> {
        val liveResult = MutableLiveData<List<Post>>()
        postRepo.getAllPosts()
                .subscribeOn(Schedulers.io())
                .subscribe { posts, _ ->
                    liveResult.postValue(posts)
                }
        return liveResult
    }
}
