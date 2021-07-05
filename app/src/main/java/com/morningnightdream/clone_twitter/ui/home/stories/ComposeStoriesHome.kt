package com.morningnightdream.clone_twitter.ui.home.stories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.morningnightdream.clone_twitter.ui.components.TweetSurface

@Composable
fun RoundedUserImage(
    url: String,
    modifier: Modifier = Modifier.requiredSize(60.dp).padding(2.dp)
) {
    TweetSurface(
        shape = CircleShape,
        modifier = modifier,
    ) {
        Image(
            painter = rememberCoilPainter(url),
            contentDescription = null
        )
    }
}