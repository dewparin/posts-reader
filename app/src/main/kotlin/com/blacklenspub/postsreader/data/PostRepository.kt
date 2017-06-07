package com.blacklenspub.postsreader.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.blacklenspub.postsreader.data.model.Post
import com.blacklenspub.postsreader.data.remote.PostsReaderApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PostRepository : PostDataSource {

    private val remoteSource by lazy {
        retrofit.create(PostsReaderApi::class.java)
    }
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    override fun getAllPosts(): LiveData<List<Post>> {
        val liveData = MutableLiveData<List<Post>>()
        remoteSource.getAllPosts()
                .subscribe {
                    liveData.value = it
                }
        return liveData
    }


    override fun getPostById(id: String): LiveData<Post> {
        val liveData = MutableLiveData<Post>()
        remoteSource.getPostById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    liveData.value = it
                }
        return liveData
    }

}