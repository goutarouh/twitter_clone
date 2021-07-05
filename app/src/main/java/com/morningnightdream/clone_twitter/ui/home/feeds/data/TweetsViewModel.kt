package com.morningnightdream.clone_twitter.ui.home.feeds.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    private val repository: TweetsRepository
): ViewModel() {

    var tweetsState by mutableStateOf<TweetState>(TweetState.Loading)
        private set

    var tweets: List<Tweet>? = null

    init {
        fetchTweets()
    }

    private fun fetchTweets() {
        viewModelScope.launch {
            tweetsState = TweetState.Loading
            tweets = repository.fetchAsync()
            if (tweets != null) {
                tweetsState = TweetState.SuccessAllTweet(tweets!!)
            }
        }
    }

}