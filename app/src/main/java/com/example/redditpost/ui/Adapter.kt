package com.example.redditpost.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redditpost.R
import com.example.redditpost.data.remote.PostData
import com.example.redditpost.utils.*
import kotlinx.android.synthetic.main.comment_layout.view.*

class Adapter(private val postData: List<PostData>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = postData.getCommentAmount()


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.author.text = postData.getCommentAuthor(position)
        holder.body.text = postData.getCommentText(position)
        holder.upVotes.text = postData.getCommentUpVotes(position).toString()
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author: TextView = itemView.comment_author
        val body: TextView = itemView.comment_body
        val upVotes: TextView = itemView.comment_up_votes
    }
}