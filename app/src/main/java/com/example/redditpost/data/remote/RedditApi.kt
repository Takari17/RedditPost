package com.example.redditpost.data.remote

import io.reactivex.Single
import retrofit2.http.GET


const val BASE_URL = "https://www.reddit.com"
const val API = "/r/androiddev/comments/b877eq/devs_of_reddit_what_is_something_that_you_learned/.json"

interface RedditApi {

    @GET(API)
    fun getAllPostData(): Single<List<PostData>>

}
