package com.example.redditpost.utils

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.redditpost.data.remote.RedditResponse


/*
Factory that returns a lazy view model reference, allows you to pass dependencies to a view model
constructor. Scoped to the underlying activity, not the fragment it's self.
 */
inline fun <reified T : ViewModel> Fragment.activityViewModelFactory(
    crossinline provider: () -> T
) = activityViewModels<T> {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            provider() as T
    }
}

//For activities
inline fun <reified T : ViewModel> AppCompatActivity.viewModelFactory(
    crossinline provider: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            provider() as T
    }
}


private fun getTitle(list: List<RedditResponse>) = list[0].data.children[0].data

private fun getComment(list: List<RedditResponse>, index: Int) = list[1].data.children[index]

fun List<RedditResponse>.getTitleAuthor() = getTitle(this).author

fun List<RedditResponse>.getTitleText() = getTitle(this).titleText

fun List<RedditResponse>.getTitleUpVotes() = getTitle(this).titleUpVotes.toString()

fun List<RedditResponse>.getRepliesAmount() = getTitle(this).repliesAmount.toString()

fun List<RedditResponse>.getCommentAuthor(index: Int) = this[1].data.children[index].data.author

fun List<RedditResponse>.getCommentUpVotes(index: Int) = getComment(this, index).data.commentUpVotes.toString()

fun List<RedditResponse>.getCommentText(index: Int) = getComment(this, index).data.commentText

fun List<RedditResponse>.getCommentAmount() = this[1].data.children.size