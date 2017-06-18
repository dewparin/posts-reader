package com.blacklenspub.postsreader.presentation.postlist

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.blacklenspub.postsreader.PostsReaderApplication
import com.blacklenspub.postsreader.R
import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.presentation.PageNavigator
import kotlinx.android.synthetic.main.activity_post_list.*

class PostListActivity : LifecycleActivity() {

    private val TAG = "show_time"

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(PostListViewModel::class.java).also {
            PostsReaderApplication.component.inject(it)
        }
    }

    private val logger = PostsReaderApplication.component.logger
    private val postAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.logDebug(TAG, "onCreate")
        setContentView(R.layout.activity_post_list)
        setupRecyclerView()

        getAllPosts()
    }

    override fun onDestroy() {
        logger.logDebug(TAG, "onDestroy")
        super.onDestroy()
    }

    private fun setupRecyclerView() {
        rvPosts.apply {
            setHasFixedSize(true)

            val linearLayoutManager = LinearLayoutManager(this@PostListActivity)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))

            postAdapter.onPostClickListener = {
                PageNavigator.gotoPostDetailActivity(this@PostListActivity, it)
            }
            adapter = postAdapter
        }
    }

    private fun getAllPosts() {
        viewModel.getAllPosts().observe(this, Observer {
            if (it != null) {
                showPosts(it)
            } else {
                Toast.makeText(this, "What! Something went wrong.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showPosts(posts: List<Post>) {
        postAdapter.posts = posts.sortedBy { it.id }
    }
}

