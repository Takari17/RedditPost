package com.example.redditpost.viewmodel

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.redditpost.data.Repository
import com.example.redditpost.data.remote.PostData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

/*
Commit:
Added caching with okhttp

replaced coroutines to Rx stuff

formatted code
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application)
    private val compositeDisposable = CompositeDisposable()
    val redditPostData = MutableLiveData<List<PostData>>()
    val networkCallSuccess = MutableLiveData<Boolean>()

    fun executeNetworkCall() {
        compositeDisposable += repository.getAllPostData
            .subscribeBy(
                onSuccess = { postData ->
                    redditPostData.postValue(postData)
                    networkCallSuccess.postValue(true)
                },
                onError = { networkCallSuccess.postValue(true) }
            )
    }

    fun openRedditPost() {
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.reddit.com/r/androiddev/comments/b877eq/devs_of_reddit_what_is_something_that_you_learned/")
        )
        getApplication<Application>().startActivity(browserIntent)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}