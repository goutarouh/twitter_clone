package com.morningnightdream.clone_twitter

import androidx.compose.runtime.Composable
import com.google.accompanist.insets.ProvideWindowInsets
import com.morningnightdream.clone_twitter.ui.components.TweetSurface
import com.morningnightdream.clone_twitter.ui.home.TwitterScaffold
import com.morningnightdream.clone_twitter.ui.theme.AlphaNearTransparent
import com.morningnightdream.clone_twitter.ui.theme.TwitterTheme

@Composable
fun TwitterUiMain() {
    ProvideWindowInsets {
        TweetSurface(
            color = TwitterTheme.colors.statusBarColor.copy(alpha = AlphaNearTransparent)
        ) {
            TwitterScaffold()
        }
    }
}