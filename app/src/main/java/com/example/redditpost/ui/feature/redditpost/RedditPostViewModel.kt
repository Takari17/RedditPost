package com.example.redditpost.ui.feature.redditpost

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditpost.R
import com.example.redditpost.data.Repository
import com.example.redditpost.data.remote.RedditResponse
import com.example.redditpost.utils.Url
import io.reactivex.rxkotlin.subscribeBy


class RedditPostViewModel(
    private val application: Application,
    private val repository: Repository
) : ViewModel() {

    private val redditPostResponse = MutableLiveData<List<RedditResponse>>()
    private val errorLiveData = MutableLiveData<Throwable>()

    fun getRedditPostData() {
        //No need to dispose a single :D
        repository.getAllPostData()
            .subscribeBy(
                onSuccess = { postList -> redditPostResponse.postValue(postList) },
                onError = { e -> errorLiveData.postValue(e) }
            )
    }

    fun openRedditPostWithToast() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.FULL_URL.text)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        application.startActivity(browserIntent)
        Toast.makeText(application, R.string.openingPost, Toast.LENGTH_SHORT).show()
    }

    fun getRedditPostResponse(): LiveData<List<RedditResponse>> = redditPostResponse

    fun getErrorLiveData(): LiveData<Throwable> = errorLiveData
}