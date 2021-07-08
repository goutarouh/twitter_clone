package com.morningnightdream.clone_twitter.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.morningnightdream.clone_twitter.ui.home.feeds.ComposeTweet
import com.morningnightdream.clone_twitter.ui.home.feeds.data.TweetState
import com.morningnightdream.clone_twitter.ui.home.feeds.data.TweetsViewModel
import com.morningnightdream.clone_twitter.ui.home.stories.ComposeStoriesHome
import com.morningnightdream.clone_twitter.ui.home.stories.UserStoriesRepository

@Composable
fun HomeScreen(
    navigateToTweet: (String) -> Unit?,
    navigateToHashTagSearch: (String) -> Unit?,
    tweetsViewModel: TweetsViewModel
) {
    val tweetState = tweetsViewModel.tweetsState
    Log.i("hasegawa", "HomeScreen ${tweetState}")
    LazyColumn {
        item {
            ComposeStoriesWithSpacing()
        }
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

@Composable
fun ComposeStoriesWithSpacing(
) {
    Column {
        Spacer(modifier = Modifier.height(2.dp))
        ComposeStoriesHome(UserStoriesRepository.fetchStories())
        Spacer(modifier = Modifier.height(2.dp))
    }
}