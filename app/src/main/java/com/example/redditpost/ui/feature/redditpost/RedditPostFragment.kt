package com.example.redditpost.ui.feature.redditpost

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditpost.R
import com.example.redditpost.data.remote.RedditResponse
import com.example.redditpost.utils.getRepliesAmount
import com.example.redditpost.utils.getTitleAuthor
import com.example.redditpost.utils.getTitleText
import com.example.redditpost.utils.getTitleUpVotes
import kotlinx.android.synthetic.main.fragment_post.*

class RedditPostFragment : Fragment() {

    private val viewModel by activityViewModels<RedditPostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRedditPostResponse().observe(viewLifecycleOwner, Observer { postData ->
            populateRecyclerView(postData)
            populateTitleViews(postData, activity!!)
        })
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
