package com.example.redditpost.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditpost.R
import com.example.redditpost.model.remote.response.*
import com.example.redditpost.view.Adapter
import com.example.redditpost.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : Fragment() {

    private lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(MyViewModel::class.java)

        viewModel.getNetworkCallLiveData().observe(viewLifecycleOwner, observeNetworkCall())
    }

    private fun observeNetworkCall(): Observer<List<PostData>> {
        return Observer { postData ->

            populateRecyclerView(postData)

            populateTitleViews(postData)
        }
    }

    private fun populateRecyclerView(postData: List<PostData>) {
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = Adapter(postData)
        }
    }

    private fun populateTitleViews(postData: List<PostData>) {
        title_author.text = "Posted by u/${postData.getAuthor(0)}"
        title_body.text = postData.getTitleText()
        title_replies_amount.text = "${postData.getRepliesAmount()} Replies"
        title_up_votes.text = "${postData.getTitleUpVotes()} UpVotes"
    }
}
