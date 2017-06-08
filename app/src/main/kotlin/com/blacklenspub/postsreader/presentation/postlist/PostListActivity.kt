package com.blacklenspub.postsreader.presentation.postlist

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import com.blacklenspub.postsreader.R

class PostListActivity : LifecycleActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(PostListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        getAllPosts()
    }

    private fun getAllPosts() {
        viewModel.getAllPosts().observe(this, Observer {
            it?.let {
                Log.d("DEW", "${it.size}")
                // TODO : update UI
            }
        })
    }
}