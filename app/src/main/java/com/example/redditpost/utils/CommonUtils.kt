package com.example.redditpost.utils

import com.example.redditpost.data.remote.PostData

fun List<PostData>.getTitleAuthor() = this[0].data.children[0].data.author

fun List<PostData>.getCommentAuthor(index: Int) = this[1].data.children[index].data.author

fun List<PostData>.getTitleText() = this[0].data.children[0].data.titleText

fun List<PostData>.getTitleUpVotes() = this[0].data.children[0].data.titleUpVotes.toString()

fun List<PostData>.getRepliesAmount() = this[0].data.children[0].data.repliesAmount.toString()

fun List<PostData>.getCommentUpVotes(index: Int) = this[1].data.children[index].data.commentUpVotes

fun List<PostData>.getCommentText(index: Int) = this[1].data.children[index].data.commentText

fun List<PostData>.getCommentAmount() = this[1].data.children.size



