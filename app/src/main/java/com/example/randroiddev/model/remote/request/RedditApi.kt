package com.example.randroiddev.model.remote.request

import com.example.randroiddev.model.remote.response.PostData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



const val BASE_URL = "https://www.reddit.com"
const val API = "/r/androiddev/comments/b877eq/devs_of_reddit_what_is_something_that_you_learned/.json"

interface RedditApi {

    @GET(API)
    fun getAllPostDataAsync(): Deferred<List<PostData>>


    companion object{
        operator fun invoke(): RedditApi {
            // setting up retrofit

            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RedditApi::class.java)
        }


    }
}