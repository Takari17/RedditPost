package com.example.randroiddev.model.remote.response

import com.google.gson.annotations.SerializedName


data class PostData(val `data`: Data)

data class Data(val children: List<Children>)

data class Children(val `data`: DataX)

data class DataX(

    @SerializedName("title")
    val titleText: String,

    @SerializedName("ups")
    val titleUpVotes: Int,

    @SerializedName("num_comments")
    val repliesAmount: Int,

    // comments only
    @SerializedName("score")
    val commentUpVotes: Int,

    @SerializedName("body")
    val commentText: String,

    val author: String
)