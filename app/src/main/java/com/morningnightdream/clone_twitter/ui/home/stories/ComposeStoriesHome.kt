package com.morningnightdream.clone_twitter.ui.home.stories

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.morningnightdream.clone_twitter.ui.components.TweetSurface

const val PHOTO_URL = "https://pbs.twimg.com/profile_images/1231284925279563777/kPkDUSnY_400x400.jpg"

@Preview
@Composable
fun Preview() {
    ComposeUserStory(userStory = UserStory("goutarouh", ""))
}

@Composable
fun ComposeStoriesHome(
    stories: List<UserStory>
) {
    LazyRow {
        item {
            ComposeUserStory(userStory = null)
        }
        items(stories.size) { index ->
            ComposeUserStory(userStory = stories[index])
        }
    }
}

@Composable
fun ComposeUserStory(
    userStory: UserStory?
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        userStory?.let {
            RoundedUserImage(
                userStory.userImage,
                modifier = Modifier
                    .requiredSize(60.dp)
                    .padding(2.dp)
                    .border(3.dp, Color.LightGray, CircleShape)
            )
        } ?: let {
            RoundedUserImage(url = "")
        }
        Spacer(modifier = Modifier.height(4.dp))
        StoryUserName(userStory = userStory)
    }
}

@Composable
fun StoryUserName(
    userStory: UserStory?
) {
    Text(
        text = userStory?.userName ?: "Add",
        modifier = Modifier.fillMaxWidth(0.6f),
        fontSize = 12.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        textAlign = TextAlign.Center
    )
}


@Composable
fun RoundedUserImage(
    url: String,
    modifier: Modifier = Modifier
        .requiredSize(60.dp)
        .padding(2.dp)
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