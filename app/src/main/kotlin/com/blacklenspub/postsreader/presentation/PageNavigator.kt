package com.blacklenspub.postsreader.presentation

import android.content.Context
import com.blacklenspub.postsreader.presentation.postdetail.PostDetailActivity

object PageNavigator {

    fun gotoPostDetailActivity(context: Context, postId: String) {
        val intent = PostDetailActivity.buildIntent(context, postId)
        context.startActivity(intent)
    }
}

