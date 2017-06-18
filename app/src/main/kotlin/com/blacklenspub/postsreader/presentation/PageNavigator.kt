package com.blacklenspub.postsreader.presentation

import android.content.Context
import android.content.Intent
import com.blacklenspub.postsreader.presentation.postdetail.PostDetailActivity

object PageNavigator {

    fun gotoPostDetailActivity(context: Context, postId: String) {
        val intent = Intent(context, PostDetailActivity::class.java).apply {
            putExtra(PostDetailActivity.KEY_POST_ID, postId)
        }
        context.startActivity(intent)
    }
}

