package com.example.randroiddev.model

import com.example.randroiddev.model.remote.request.RequestData

class Repository {

    private val requestData = RequestData()

    fun getNetworkCallLiveData() = requestData.networkCall

    fun getShowPostFragment() = requestData.showPostFragment

    fun executeNetworkCall() = requestData.executeNetworkCall()
}
