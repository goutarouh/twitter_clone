package com.morningnightdream.clone_twitter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.morningnightdream.clone_twitter.ui.home.HomeScreen
import com.morningnightdream.clone_twitter.ui.home.feeds.data.Tweet
import com.morningnightdream.clone_twitter.ui.home.feeds.data.TweetsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: TweetsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeScreen(
                navigateToTweet = { t ->
                    Log.i("hasegawa", "onCreate")
                    Unit
                },
                navigateToHashTagSearch = { t ->
                    Log.i("hasegawa", "onCreate")
                    Unit
                },
                tweetsViewModel = viewModel
            )
        }
    }
}


@Composable
fun TestLooperManager(
) {
    val text = remember {
        mutableStateOf("")
    }
    Log.i("hasegawa", "TestLooperManager")
    Text(
        text = "Hello Android ${text.value}",
        modifier = Modifier
            .fillMaxSize(),
        textAlign = TextAlign.Center,
        onTextLayout = {
            text.value = "gfoutarouh"
        }
    )
}
