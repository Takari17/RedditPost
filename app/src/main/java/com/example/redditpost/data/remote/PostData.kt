package com.example.redditpost.data.remote

import com.google.gson.annotations.SerializedName

data class PostData(val `data`: Users)

data class Users(val children: List<Children>)

data class Children(val `data`: UserData)

data class UserData(

    @SerializedName("title")
    val titleText: String,

    @SerializedName("body")
    val commentText: String,

    @SerializedName("ups")
    val titleUpVotes: Int,

    @SerializedName("score")
    val commentUpVotes: Int,

    @SerializedName("num_comments")
    val repliesAmount: Int,

    val author: String
)
