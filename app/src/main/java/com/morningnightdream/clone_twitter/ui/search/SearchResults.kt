package com.morningnightdream.clone_twitter.ui.search

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.morningnightdream.clone_twitter.R
import com.morningnightdream.clone_twitter.ui.components.TweetSurface
import com.morningnightdream.clone_twitter.ui.theme.AlphaNearOpaque
import com.morningnightdream.clone_twitter.ui.theme.AlphaNearTransparent
import com.morningnightdream.clone_twitter.ui.theme.TwitterTheme

@Composable
fun SearchResults(searchVm: SearchTabViewModel) {
    val searchItems = searchVm.searchTabState
    val searchHeader = searchVm.searchHeaderState

    LazyColumn {
        if (searchHeader != null) {
            item {
                ComposeSearchHeader(searchHeader)
            }
        }
        item {
            Divider(
                color = TwitterTheme.colors.uiBorder.copy(AlphaNearTransparent),
                thickness = 5.dp
            )
        }
        if (searchItems is SearchState.Success) {
            items(searchItems.searchData.size) {
                searchItems.searchData.forEach { searchTwitter ->
                    ComposeSearchItems(searchTwitter)
                }
            }
        }
    }
}

@Composable
fun ComposeSearchItems(
    searchTwitter: SearchTwitter
) {
   Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LeftContent(searchTwitter)
            OtherEnd(searchTwitter)
        }
        Divider(
            color = Color.Gray.copy(AlphaNearOpaque),
            thickness = 0.5.dp
        )
    }
}

@Composable
private fun LeftContent(searchTwitter: SearchTwitter) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(8.dp)
    ) {
        Text(
            text = searchTwitter.searchCategory,
            fontWeight = FontWeight.Bold,
            color = TwitterTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = searchTwitter.hashTagTitle,
            color = TwitterTheme.colors.textSecondary,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = searchTwitter.totalTweets,
            color = TwitterTheme.colors.textPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun OtherEnd(
    searchTwitter: SearchTwitter
) {
    if (searchTwitter.imageUrl == null) {
        Icon(
            painterResource(id = R.drawable.ic_vector_overflow),
            modifier = Modifier.padding(12.dp),
            contentDescription = null,
            tint = Color.Gray
        )
    } else {
        Image(
            rememberCoilPainter(searchTwitter.imageUrl),
            modifier = Modifier.padding(12.dp),
            contentDescription = null
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF
)
@Composable
fun Test() {
    Column {
        Text(
            text = "gouta"
        )
        Text(
            text = "gouta",
            color = Color(0xFF000000),
        )
    }
}