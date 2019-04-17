package com.example.randroiddev.viewmodel

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.example.randroiddev.model.Repository

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository()

    fun executeNetworkCall() = repository.executeNetworkCall()

    fun getNetworkCallLiveData() = repository.getNetworkCallLiveData()

    fun getShowPostFragment() = repository.getShowPostFragment()

    fun openRedditPost() {
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.reddit.com/r/androiddev/comments/b877eq/devs_of_reddit_what_is_something_that_you_learned/")
        )
        getApplication<Application>().startActivity(browserIntent)
    }
}