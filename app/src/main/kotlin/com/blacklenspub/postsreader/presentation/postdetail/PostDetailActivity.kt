package com.blacklenspub.postsreader.presentation.postdetail

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import com.blacklenspub.postsreader.PostsReaderApplication
import com.blacklenspub.postsreader.R
import com.blacklenspub.postsreader.data.entity.Post
import com.blacklenspub.postsreader.data.local.AppDatabase
import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : LifecycleActivity() {

    private val TAG = PostDetailActivity::javaClass.name

    companion object {
        val KEY_POST_ID = "postId"
    }

    private lateinit var postId: String

    private val viewModel: PostDetailViewModel by lazy {
        ViewModelProviders.of(this).get(PostDetailViewModel::class.java).also {
            PostsReaderApplication.component.inject(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        intent?.let {
            retrieveIntentData(it)
            getPostDetail()
        }
    }

    private fun retrieveIntentData(data: Intent) {
        postId = data.getStringExtra(KEY_POST_ID)
    }

    private fun getPostDetail() {
        viewModel.getPostDetail(postId).observe(this, Observer {
            if (it != null) {
                setPostDetail(it)
                // TODO : uncomment below statement and then observe result.
                // modifyPostFromOtherThread(it)
            } else {
                Log.d(TAG, "ERROR # null post data")
                Toast.makeText(this, "What! Something went wrong.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setPostDetail(post: Post) {
        Log.d(TAG, "Displaying post detail for post id ${post.id}")
        tvPostTitle.text = post.title
        tvPostBody.text = post.body
    }

    // this ugly function is just for demonstration purpose.
    private var isPostModified = false
    private fun modifyPostFromOtherThread(post: Post) {
        if (!isPostModified) {
            Thread(Runnable {
                SystemClock.sleep(3000)
                post.title = "<-- Hacked by Black Lens Crew -->"
                post.body = "LOL : 55555"
                viewModel.updatePost(post)
            }).start()
            isPostModified = true
        }
    }
}

