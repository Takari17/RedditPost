package com.example.redditpost.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.redditpost.view.fragments.FailedFragment
import com.example.redditpost.view.fragments.PostFragment
import com.example.redditpost.view.fragments.ProgressFragment
import com.example.redditpost.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_progress.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.redditpost.R.layout.activity_main)

        addProgressFragment() // Shows progress bar immediately on start, will change after Network Call

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        viewModel.getShowPostFragment().observe(this, getObserver())

        viewModel.executeNetworkCall()
    }

    private fun addProgressFragment() {
        val progressFragment = ProgressFragment()
        replaceContainer(progressFragment)
    }

    private fun addCommentFragment() {
        val commentFragment = PostFragment()
        replaceContainer(commentFragment)
    }

    private fun addFailedFragment() {
        val failedFragment = FailedFragment()
        replaceContainer(failedFragment)
    }

    private fun replaceContainer(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(com.example.redditpost.R.id.fragment_container, fragment)
            .commit()
    }

    private fun getObserver(): Observer<Boolean> {
        return Observer { networkCallSuccess ->
            progressBar.visibility = View.INVISIBLE
            if (networkCallSuccess) addCommentFragment() else addFailedFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(com.example.redditpost.R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        viewModel.openRedditPost()
        return true
    }
}
