package com.example.redditpost.ui.feature.redditpost

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.redditpost.R
import com.example.redditpost.data.Repository
import com.example.redditpost.data.remote.RedditApi
import com.example.redditpost.data.remote.RedditResponse
import com.example.redditpost.utils.Url
import io.reactivex.rxkotlin.subscribeBy

class RedditPostViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(RedditApi.invoke())
    private val redditPostResponse = MutableLiveData<List<RedditResponse>>()
    private val networkCallSuccess = MutableLiveData<Boolean>()

    fun executeNetworkCall() =
        //No need to dispose a single :D
        repository.getAllPostData
            .subscribeBy(
                onSuccess = { postList ->
                    redditPostResponse.postValue(postList)
                    networkCallSuccess.postValue(true)
                },
                onError = { e ->
                    networkCallSuccess.postValue(false)
                    Log.d("zwi", "executeNetworkCall error: $e")
                }
            )

    fun openRedditPostWithToast() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.FULL_URL.text)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        getApplication<Application>().startActivity(browserIntent)
        Toast.makeText(getApplication(), R.string.openingPost, Toast.LENGTH_SHORT).show()
    }

    fun getRedditPostResponse(): LiveData<List<RedditResponse>> = redditPostResponse

    fun getNetworkCallSuccess(): LiveData<Boolean> = networkCallSuccess
}