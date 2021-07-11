package com.morningnightdream.clone_twitter.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.material.tabs.TabLayout
import com.morningnightdream.clone_twitter.ui.components.TweetSurface
import com.morningnightdream.clone_twitter.ui.theme.TwitterTheme

@Composable
fun SearchScreen(
    modifierPadding: PaddingValues,
    hashTagParams: String?
) {
    val searchVm: SearchTabViewModel = hiltViewModel()
    TweetSurface(
        modifier = Modifier.padding(modifierPadding)
    ) {
        val selectedTab = remember {
            mutableStateOf(SearchTab.ForYou)
        }
        val tabTitles = SearchTab.values().map { it.title }
        Column {
            ScrollableTab(selectedTab, tabTitles)
            SearchResults(searchVm)
        }
    }
}

@Composable
fun ScrollableTab(
    selectedTab: MutableState<SearchTab>,
    tabTitles: List<String>
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab.value.ordinal,
        backgroundColor = TwitterTheme.colors.uiBackground,
        edgePadding = 0.dp,
        contentColor = TwitterTheme.colors.accent
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = isSelected(index, selectedTab),
                onClick = {
                    selectedTab.value = SearchTab.values().first { it.title == title }
                },
                text = {
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = textColor(isSelected(index, selectedTab))
                    )
                }
            )
        }
    }
}

@Composable
fun textColor(selected: Boolean): Color {
    return if (selected) {
        TwitterTheme.colors.accent
    } else {
        TwitterTheme.colors.textSecondary
    }
}

@Composable
private fun isSelected(
    index: Int,
    selectedTab: MutableState<SearchTab>
): Boolean {
    return index == selectedTab.value.ordinal
}