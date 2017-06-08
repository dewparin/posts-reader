package com.blacklenspub.postsreader.presentation.postlist

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.blacklenspub.postsreader.R
import com.blacklenspub.postsreader.data.model.Post

class PostListActivity : LifecycleActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(PostListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)

        getAllPosts()
    }

    private fun getAllPosts() {
        viewModel.getAllPosts().observe(this, Observer {
            if (it != null) {
                showPosts(it)
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showPosts(posts: List<Post>) {
        Log.d("DEW", "${posts.size}")
        // TODO : update UI
    }
}