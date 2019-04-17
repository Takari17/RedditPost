package com.example.randroiddev.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.randroiddev.R
import com.example.randroiddev.model.remote.response.*
import kotlinx.android.synthetic.main.comment_layout.view.*

class Adapter(private val postData: List<PostData>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = postData.getCommentAmount()


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.author.text = postData.getAuthor(1, position)
        holder.body.text = postData.getCommentText(position)
        holder.upVotes.text = postData.getCommentUpVotes(position).toString()
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author = itemView.comment_author
        val body = itemView.comment_body
        val upVotes = itemView.comment_up_votes
    }
}