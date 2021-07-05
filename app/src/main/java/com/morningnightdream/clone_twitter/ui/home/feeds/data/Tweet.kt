package com.morningnightdream.clone_twitter.ui.home.feeds.data

data class Tweet(
    val tUid: String = "",
    val tUName: String = "",
    val tUImage: String = "",
    val tUText: String = "",
    val tUTime: Long = 0,
    val tUHandler: String = "",
    val tCommentCount: Long = 0,
    val tLikeCount: Long = 0,
    val tRTCount: Long = 0,
    val metadata: TweetUrlMeta? = null
)

sealed class TweetState {
    object Loading: TweetState()
    class SuccessAllTweet(var data: List<Tweet>): TweetState()
    class SuccessTweet(var data: Tweet): TweetState()
}

data class TweetUrlMeta(
    var title: String? = null,
    var desc: String? = null,
    var image: String? = null,
    var url: String? = null
)