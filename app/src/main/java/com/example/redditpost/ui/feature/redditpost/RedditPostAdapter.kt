package com.example.redditpost.ui.feature.redditpost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redditpost.R
import com.example.redditpost.data.remote.RedditResponse
import com.example.redditpost.utils.getCommentAmount
import com.example.redditpost.utils.getCommentAuthor
import com.example.redditpost.utils.getCommentText
import com.example.redditpost.utils.getCommentUpVotes
import kotlinx.android.synthetic.main.comment_layout.view.*

class RedditPostAdapter(private val postData: List<RedditResponse>) :
    RecyclerView.Adapter<RedditPostAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = postData.getCommentAmount()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            author.text = postData.getCommentAuthor(position)
            body.text = postData.getCommentText(position)
            upVotes.text = postData.getCommentUpVotes(position)
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author: TextView = itemView.comment_author
        val body: TextView = itemView.comment_body
        val upVotes: TextView = itemView.comment_up_votes
    }
}