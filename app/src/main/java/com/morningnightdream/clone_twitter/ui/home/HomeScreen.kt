package com.morningnightdream.clone_twitter.ui.home

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.morningnightdream.clone_twitter.ui.home.feeds.ComposeTweet
import com.morningnightdream.clone_twitter.ui.home.feeds.data.TweetState
import com.morningnightdream.clone_twitter.ui.home.feeds.data.TweetsViewModel

@Composable
fun HomeScreen(
    navigateToTweet: (String) -> Unit?,
    navigateToHashTagSearch: (String) -> Unit?,
    tweetsViewModel: TweetsViewModel
) {
    val tweetState = tweetsViewModel.tweetsState
    Log.i("hasegawa", "HomeScreen ${tweetState}")
    LazyColumn {
        when (tweetState) {
            is TweetState.SuccessAllTweet -> {
                item {
                    tweetState.data.forEach {
                        ComposeTweet(
                            tweet = it,
                            onUrlRecognized = { tweet, s ->
                                Log.i("hasegawa", "HomeScreen1")
                            },
                            onClickTweet = { tweet ->
                                Log.i("hasegawa", "onClickTweet")
                            },
                            hashTagNavigator = { hashTag ->
                                Log.i("hasegawa", hashTag)
                            }
                        )
                    }
                }
            }
        }
    }
}