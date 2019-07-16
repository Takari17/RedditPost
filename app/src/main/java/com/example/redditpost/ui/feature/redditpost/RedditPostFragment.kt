package com.example.redditpost.ui.feature.redditpost

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditpost.R
import com.example.redditpost.data.Repository
import com.example.redditpost.data.remote.RedditApi
import com.example.redditpost.data.remote.RedditResponse
import com.example.redditpost.utils.*
import kotlinx.android.synthetic.main.reddit_post_fragment.*


class RedditPostFragment : Fragment() {

    private val viewModel by activityViewModelFactory {
        RedditPostViewModel(activity?.application!!, Repository(RedditApi.invoke()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.reddit_post_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRedditPostResponse().observe(viewLifecycleOwner, Observer { postData ->
            makeViewsVisible()
            makeProgressBarInvisible()
            populateRecyclerView(postData)
            populateTitleViews(postData, activity!!)
        })

        viewModel.getRedditPostData()
    }

    private fun makeViewsVisible(){
        fragment_title_layout.visibility = View.VISIBLE
        recycler_view.visibility = View.VISIBLE
    }

    private fun makeProgressBarInvisible(){
        progressBar.visibility = View.VISIBLE
    }

    private fun populateRecyclerView(postData: List<RedditResponse>) {
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = RedditPostAdapter(postData)
        }
    }

    private fun populateTitleViews(postData: List<RedditResponse>, context: Context) {
        title_author.text = context.getString(R.string.userName, postData.getTitleAuthor())
        title_body.text = postData.getTitleText()
        title_replies_amount.text = context.getString(R.string.repliesAmount, postData.getRepliesAmount())
        title_up_votes.text = context.getString(R.string.upvoteAmount, postData.getTitleUpVotes())
    }
}
