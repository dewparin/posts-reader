package com.blacklenspub.postsreader.presentation.postlist

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.blacklenspub.postsreader.PostsReaderApplication
import com.blacklenspub.postsreader.R
import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.presentation.postdetail.PostDetailActivity
import kotlinx.android.synthetic.main.activity_post_list.*

class PostListActivity : LifecycleActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(PostListViewModel::class.java).also {
            PostsReaderApplication.component.inject(it)
        }
    }

    private val postAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        setupRecyclerView()

        getAllPosts()
    }

    private fun setupRecyclerView() {
        rvPosts.apply {
            setHasFixedSize(true)

            val linearLayoutManager = LinearLayoutManager(this@PostListActivity)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))

            postAdapter.onPostClickListener = {
                val intent = PostDetailActivity.buildIntent(this@PostListActivity, it)
                startActivity(intent)
            }
            adapter = postAdapter
        }
    }

    private fun getAllPosts() {
        viewModel.getAllPosts().observe(this, Observer {
            if (it != null) {
                Log.d("Dew", "PostListActivity # Got ${it.size} posts. Thread ID ${Thread.currentThread().id}")
                showPosts(it)
            } else {
                Log.d("Dew", "PostListActivity # ERROR : Thread ID ${Thread.currentThread().id}")
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showPosts(posts: List<Post>) {
        postAdapter.posts = posts.sortedBy { it.id }
    }
}

