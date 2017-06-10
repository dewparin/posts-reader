package com.blacklenspub.postsreader.presentation.postlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blacklenspub.postsreader.R
import com.blacklenspub.postsreader.data.entity.Post
import kotlin.properties.Delegates

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var posts by Delegates.observable(listOf<Post>()) { _, _, _ -> notifyDataSetChanged() }
    var onPostClickListener: ((id: String) -> Unit)? = null

    override fun getItemCount() = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view, onPostClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.post = posts[position]
    }

    class ViewHolder(view: View, onPostClickListener: ((id: String) -> Unit)?) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = view.findViewById(R.id.tvPostTitle) as TextView

        var post: Post by Delegates.observable(Post("", "", "")) { _, _, postItem ->
            tvTitle.text = postItem.title
        }

        init {
            view.setOnClickListener { onPostClickListener?.invoke(post.id) }
        }
    }
}

