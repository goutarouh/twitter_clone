package com.morningnightdream.clone_twitter

import android.os.Bundle
import android.util.Log
import android.widget.TextView
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
import androidx.compose.ui.tooling.preview.Preview
import com.morningnightdream.clone_twitter.ui.home.HomeScreen
import com.morningnightdream.clone_twitter.ui.home.TwitterScaffold
import com.morningnightdream.clone_twitter.ui.home.feeds.data.Tweet
import com.morningnightdream.clone_twitter.ui.home.feeds.data.TweetsViewModel
import com.morningnightdream.clone_twitter.ui.theme.ProvideTwitterColors
import com.morningnightdream.clone_twitter.ui.theme.TwitterTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TwitterTheme {
               TwitterUiMain()
            }
        }
    }
}


@Preview("Main Activity")
@Composable
fun Preview(
) {
}