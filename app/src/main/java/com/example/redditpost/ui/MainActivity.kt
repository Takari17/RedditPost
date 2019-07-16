package com.example.redditpost.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.example.redditpost.R
import com.example.redditpost.data.Repository
import com.example.redditpost.data.remote.RedditApi
import com.example.redditpost.ui.feature.common.FailedFragment
import com.example.redditpost.ui.feature.redditpost.RedditPostFragment
import com.example.redditpost.ui.feature.redditpost.RedditPostViewModel
import com.example.redditpost.utils.viewModelFactory


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModelFactory { RedditPostViewModel(application, Repository(RedditApi.invoke())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addRedditPostFragment()

        viewModel.getErrorLiveData().observe(this, Observer {
            addFailedFragment()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        viewModel.openRedditPostWithToast()
        return true
    }


    private fun addRedditPostFragment() {
        replaceContainer(RedditPostFragment())
    }

    private fun addFailedFragment() {
        replaceContainer(FailedFragment())
    }

    private fun replaceContainer(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}