package com.example.redditpost.model

import com.example.redditpost.model.remote.request.RequestData

class Repository {

    private val requestData = RequestData()

    fun getNetworkCallLiveData() = requestData.networkCall

    fun getShowPostFragment() = requestData.showPostFragment

    fun executeNetworkCall() = requestData.executeNetworkCall()
}
