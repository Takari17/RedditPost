package com.example.redditpost.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditpost.R
import com.example.redditpost.data.remote.PostData
import com.example.redditpost.ui.Adapter
import com.example.redditpost.utils.getRepliesAmount
import com.example.redditpost.utils.getTitleAuthor
import com.example.redditpost.utils.getTitleText
import com.example.redditpost.utils.getTitleUpVotes
import com.example.redditpost.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        viewModel.redditPostData.observe(viewLifecycleOwner, Observer { postData ->
            populateRecyclerView(postData)
            populateTitleViews(postData, activity!!)
        })
    }


    private fun populateRecyclerView(postData: List<PostData>) {
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = Adapter(postData)
        }
    }

    private fun populateTitleViews(postData: List<PostData>, context: Context) {
        title_author.text = context.getString(R.string.userName, postData.getTitleAuthor())
        title_body.text = postData.getTitleText()
        title_replies_amount.text = context.getString(R.string.repliesAmount, postData.getRepliesAmount())
        title_up_votes.text = context.getString(R.string.upvoteAmount, postData.getTitleUpVotes())
    }
}
