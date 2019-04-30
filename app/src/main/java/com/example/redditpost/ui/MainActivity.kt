package com.example.redditpost.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.redditpost.ui.fragments.FailedFragment
import com.example.redditpost.ui.fragments.PostFragment
import com.example.redditpost.ui.fragments.ProgressFragment
import com.example.redditpost.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.redditpost.R.layout.activity_main)

        addProgressBarFragment() // Shows progress bar immediately on start, will change after Network Call

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.networkCallSuccess.observe(this, Observer { success ->
            if (success) addPostFragment() else addFailedFragment()
        })

        viewModel.executeNetworkCall()
    }

    private fun addProgressBarFragment() {
        val progressFragment = ProgressFragment()
        replaceContainer(progressFragment)
    }

    private fun addPostFragment() {
        val postFragment = PostFragment()
        replaceContainer(postFragment)
    }

    private fun addFailedFragment() {
        val failedFragment = FailedFragment()
        replaceContainer(failedFragment)
    }

    private fun replaceContainer(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(com.example.redditpost.R.id.fragment_container, fragment)
            .commit()
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