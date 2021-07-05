package com.morningnightdream.clone_twitter.ui.home.feeds

import android.text.format.DateUtils
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morningnightdream.clone_twitter.R
import com.morningnightdream.clone_twitter.ui.components.ComposeTweetFeedText
import com.morningnightdream.clone_twitter.ui.components.TweetSurface
import com.morningnightdream.clone_twitter.ui.home.feeds.data.Tweet
import com.morningnightdream.clone_twitter.ui.home.stories.RoundedUserImage
import java.util.*


@Preview
@Composable
fun Preview() {
    Calendar.getInstance().apply {
        set(Calendar.MONTH, 5)
    }.timeInMillis
    val tweet = Tweet(
        tUName = "gouta",
        tUHandler = "@goutarouhaa",
        tUText = "I went to a beach. It was the boss of beaches in the world. I was really chilled out. #beach #life",
        tCommentCount = 10,
        tRTCount = 123,
        tLikeCount = 12,
        tUTime = 1622759288032
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        ComposeTweet(
            tweet = tweet,
            onUrlRecognized = { a, b ->
                Log.i("hasetgawa", "$a, $b")
            },
            hashTagNavigator = {
                Log.i("hasegawa", "Preview $it")
            },
            onClickTweet = {
                Log.i("hasegawa", "Preview $it")
            }
        )
    }
}

@Composable
fun ComposeTweet(
    tweet: Tweet,
    onUrlRecognized: (Tweet, String) -> Unit,
    onClickTweet: (Tweet) -> Unit,
    hashTagNavigator: (String) -> Unit
) {
    TweetSurface(
        modifier = Modifier.clickable {
            onClickTweet.invoke(tweet)
        },
        color = Color.White
    ) {
        Column {
            Row(
                modifier = Modifier.padding(12.dp)
            ) {
                RoundedUserImage(url = tweet.tUImage)
                Spacer(modifier = Modifier.width(14.dp))
                ComposeTweetColumn(
                    tweet = tweet,
                    onUrlRecognized = onUrlRecognized,
                    hashTagNavigator = hashTagNavigator,
                    onClickTweet = onClickTweet
                )
            }
            Divider(color = Color.Gray)
        }
    }
}

@Composable
fun ComposeTweetColumn(
    tweet: Tweet,
    onUrlRecognized: (Tweet, String) -> Unit,
    hashTagNavigator: (String) -> Unit,
    onClickTweet: (Tweet) -> Unit
) {
    Column {
        ComposeNameHandlerOverflow(
            name = tweet.tUName,
            tUHandler = tweet.tUHandler,
            showOverflow = true
        )
        ComposeTime(tweet.tUTime)
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTweetFeedText(
            text = tweet.tUText,
            urlRecognizer = { },
            hashTagNavigator = hashTagNavigator,
            textClick = { }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ComposeFooter(tweet)
    }
}

@Composable
fun ComposeNameHandlerOverflow(
    name: String,
    tUHandler: String,
    showOverflow: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = tUHandler,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (showOverflow) {
            Icon(
                painterResource(id = R.drawable.ic_vector_overflow),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun ComposeTime(time: Long, color: Color? = null) {
    Text(
        DateUtils.getRelativeTimeSpanString(
            time,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp,
        color = color ?: Color.Black
    )
}


@Composable
fun ComposeFooter(tweet: Tweet) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_reply),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = tweet.tCommentCount.toString(),
                modifier = Modifier.padding(start = 4.dp),
                fontSize = 14.sp
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_retweet_stroke),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text =tweet.tRTCount.toString(),
                modifier = Modifier.padding(start = 4.dp),
                fontSize = 14.sp
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_heart_stroke),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text =tweet.tLikeCount.toString(),
                modifier = Modifier.padding(start = 4.dp),
                fontSize = 14.sp
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_share_android),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}