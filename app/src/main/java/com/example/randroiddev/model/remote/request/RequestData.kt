package com.example.randroiddev.model.remote.request

import androidx.lifecycle.MutableLiveData
import com.example.randroiddev.model.remote.response.PostData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RequestData {

    private val redditApi = RedditApi.invoke()
    val networkCall = MutableLiveData<List<PostData>>()
    val showPostFragment = MutableLiveData<Boolean>()

    fun executeNetworkCall() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                networkCall.value = redditApi.getAllPostDataAsync() // Observed by Fragment
                    .await()

                showPostFragment.postValue(true) // Observed by activity

            } catch (e: Exception) {
                showPostFragment.postValue(false)
            }
        }
    }
}
