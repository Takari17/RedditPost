package com.example.redditpost.data

import com.example.redditpost.data.remote.RedditApi
import io.reactivex.schedulers.Schedulers

class Repository(private val redditApi: RedditApi) {

    fun getAllPostData() = redditApi.getAllPostData()
        .subscribeOn(Schedulers.io())
}