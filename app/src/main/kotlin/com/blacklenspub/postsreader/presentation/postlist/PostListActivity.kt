package com.blacklenspub.postsreader.presentation.postlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.blacklenspub.postsreader.R
import io.reactivex.disposables.CompositeDisposable

class PostListActivity : AppCompatActivity() {

    private val viewModel by lazy { PostListViewModel() }
    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        getAllPosts()
    }

    private fun getAllPosts() {
        val disposable = viewModel.getAllPosts().subscribe {
            // TODO : update UI
        }
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}