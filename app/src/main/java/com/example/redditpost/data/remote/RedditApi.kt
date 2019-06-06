package com.example.redditpost.data.remote

import com.example.redditpost.utils.Url
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val API = "/r/androiddev/comments/b877eq/devs_of_reddit_what_is_something_that_you_learned/.json"

interface RedditApi {


    @GET(API) //Using an enum didn't work here :/
    fun getAllPostData(): Single<List<RedditResponse>>


    companion object {
        private var redditApi: RedditApi? = null

        operator fun invoke(): RedditApi {
            if (redditApi == null) {
                redditApi = Retrofit.Builder()
                    .baseUrl(Url.BASE_URL.text)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RedditApi::class.java)
            }
            return redditApi!!
        }
    }
}
