package com.blacklenspub.postsreader.presentation.postdetail

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
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

    companion object {

        private val KEY_POST_ID = "postId"

        fun buildIntent(context: Context, postId: String) =
                Intent(context, PostDetailActivity::class.java).apply {
                    putExtra(KEY_POST_ID, postId)
                }
    }

    lateinit var postId: String

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
                Log.d("Dew", "PostDetailActivity # Thread ID ${Thread.currentThread().id}")
                setPostDetail(it)
                // TODO : uncomment below statement and then observe result.
                // modifyPostFromOtherThread(it)
            } else {
                Log.d("Dew", "PostDetailActivity # ERROR Thread ID ${Thread.currentThread().id}")
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setPostDetail(post: Post) {
        tvPostTitle.text = post.title
        tvPostBody.text = post.body
    }

    // this ugly function is just for demonstration purpose.
    private var isPostModified = false
    private fun modifyPostFromOtherThread(post: Post) {
        if (!isPostModified) {
            Thread(Runnable {
                SystemClock.sleep(3000)
                post.title = "Hacked by Black Lens Crew"
                post.body = "LOL : 55555"
                AppDatabase.instance.postDao().insertOrUpdatePosts(post)
            }).start()
            isPostModified = true
        }
    }
}

