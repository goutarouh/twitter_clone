package com.morningnightdream.clone_twitter.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morningnightdream.clone_twitter.R
import com.morningnightdream.clone_twitter.ui.components.TweetSurface
import com.morningnightdream.clone_twitter.ui.theme.TwitterTheme

@Composable
fun TwitterTopAppBar(
    shouldShowSearch: Boolean,
    function: () -> Unit
) {
    TweetSurface(
        color = TwitterTheme.colors.uiBackground,
        contentColor = TwitterTheme.colors.accent,
        elevation = 4.dp
    ) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (shouldShowSearch) {
                        TweetSurface(
                            color = TwitterTheme.colors.searchBarBg,
                            shape = RoundedCornerShape(25.dp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = "Search Twitter",
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                color = TwitterTheme.colors.textSecondary,
                                fontSize = 14.sp
                            )
                        }
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_twitter),
                            contentDescription = null
                        )
                    }
                }
            },
            backgroundColor = TwitterTheme.colors.uiBackground,
            navigationIcon = {
                IconButton(onClick = function) {
                    Icon(Icons.Filled.Menu, null)
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_timeline),
                        contentDescription = null)

                }
            },
            elevation = 4.dp
        )
    }
}

