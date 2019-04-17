package com.example.redditpost.model.remote.response

// With "zeroOrOne",  0 = title,  1 = comments.
fun List<PostData>.getAuthor(zeroOrOne: Int, index: Int = 0) = this[zeroOrOne].data.children[index].data.author

fun List<PostData>.getTitleText() = this[0].data.children[0].data.titleText

fun List<PostData>.getTitleUpVotes() = this[0].data.children[0].data.titleUpVotes

fun List<PostData>.getRepliesAmount() = this[0].data.children[0].data.repliesAmount

fun List<PostData>.getCommentUpVotes(index: Int) = this[1].data.children[index].data.commentUpVotes

fun List<PostData>.getCommentText(index: Int) = this[1].data.children[index].data.commentText

fun List<PostData>.getCommentAmount() = this[1].data.children.size


