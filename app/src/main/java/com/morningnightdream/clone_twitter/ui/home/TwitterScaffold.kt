package com.morningnightdream.clone_twitter.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.morningnightdream.clone_twitter.ui.home.bottomnavigation.TwitterHomeBottomAppBar
import com.morningnightdream.clone_twitter.ui.theme.TwitterTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun Preview(
) {
    TwitterScaffold()
}

@Composable
fun TwitterScaffold() {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val (shouldShowAppBar, updateAppBarVisibility) = remember { mutableStateOf(true) }
    val (shouldShowSearch, updateSearchBarVisibility) = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    val navActions = remember(navController) {
        MainActions(
            navController,
            updateAppBarVisibility,
            scaffoldState,
            coroutineScope,
            updateSearchBarVisibility
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
                 TwitterHomeTopBar(
                     scaffoldState = scaffoldState,
                     shouldShowAppBar = shouldShowAppBar,
                     scope = coroutineScope,
                     shouldShowSearch = shouldShowSearch
                 )
        },
        drawerContent = {
                        Box(modifier = Modifier
                            .size(50.dp, 50.dp)
                            .background(Color.Red))
        },
        bottomBar = {
                    TwitterHomeBottomAppBar(
                        switchBottomTab = navActions.switchBottomTab,
                        navController = navController,
                        shouldShowAppBar = shouldShowAppBar
                    )
        },
        drawerShape = MaterialTheme.shapes.medium,
        drawerElevation = DrawerDefaults.Elevation,
        drawerBackgroundColor = TwitterTheme.colors.uiBackground,
        drawerContentColor = TwitterTheme.colors.textSecondary,
        drawerScrimColor = TwitterTheme.colors.uiBorder,
        backgroundColor = TwitterTheme.colors.uiBackground,
        contentColor = TwitterTheme.colors.textSecondary
    ) { padding ->
        TwitterNavigationHost(
            navController = navController,
            padding = padding,
            shouldShowAppBar = updateAppBarVisibility,
            navAction = navActions,
            shouldShowSearchBar = updateSearchBarVisibility
        )
    }
}

@Composable
fun TwitterHomeTopBar(
    scaffoldState: ScaffoldState,
    shouldShowAppBar: Boolean,
    scope: CoroutineScope,
    shouldShowSearch: Boolean
) {
    if (shouldShowAppBar) {
        TwitterTopAppBar(shouldShowSearch = shouldShowSearch) {
            scope.launch {
                if (scaffoldState.drawerState.isOpen) {
                    scaffoldState.drawerState.close()
                } else {
                    scaffoldState.drawerState.open()
                }
            }
        }
    }
}