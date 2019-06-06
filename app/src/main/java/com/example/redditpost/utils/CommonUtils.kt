package com.example.redditpost.utils

import com.example.redditpost.data.remote.RedditResponse

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