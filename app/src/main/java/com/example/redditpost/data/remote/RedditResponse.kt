package com.example.redditpost.data.remote

import com.google.gson.annotations.SerializedName

data class RedditResponse(val `data`: UserList)

data class UserList(val children: List<User>)

data class User(val `data`: UserData)

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
