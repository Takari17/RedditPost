package com.example.redditpost.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.redditpost.R
import com.example.redditpost.ui.feature.redditpost.RedditPostFragment
import com.example.redditpost.ui.feature.redditpost.RedditPostViewModel
import com.example.redditpost.ui.feature.common.FailedFragment
import com.example.redditpost.ui.feature.common.ProgressFragment


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<RedditPostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addProgressBarFragment() // Shows progress bar immediately on start, will change after Network Call

        viewModel.getNetworkCallSuccess().observe(this, Observer { success ->
            if (success) addRedditPostFragment() else addFailedFragment()
        })

        viewModel.executeNetworkCall()
    }

    private fun addProgressBarFragment() =
        replaceContainer(ProgressFragment())


    private fun addRedditPostFragment() =
        replaceContainer(RedditPostFragment())


    private fun addFailedFragment() =
        replaceContainer(FailedFragment())


    private fun replaceContainer(fragment: Fragment) =
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        viewModel.openRedditPost()
        return true
    }
}