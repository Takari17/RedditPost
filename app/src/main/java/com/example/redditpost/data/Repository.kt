package com.example.redditpost.data

import android.app.Application
import com.example.redditpost.data.remote.ServiceGenerator
import io.reactivex.schedulers.Schedulers

class Repository(application: Application) {

    private val serviceGenerator = ServiceGenerator(application)
    private val redditApi = serviceGenerator.redditApi

    val getAllPostData = redditApi.getAllPostData()
        .subscribeOn(Schedulers.io())
}