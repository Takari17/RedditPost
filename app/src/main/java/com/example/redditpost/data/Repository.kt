package com.example.redditpost.data

import com.example.redditpost.data.remote.RedditApi
import io.reactivex.schedulers.Schedulers

class Repository(redditApi: RedditApi) {

    val getAllPostData = redditApi.getAllPostData()
        .subscribeOn(Schedulers.io())
}