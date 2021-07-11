package com.morningnightdream.clone_twitter.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomNavigation
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.morningnightdream.clone_twitter.ui.components.HASH_TAG
import com.morningnightdream.clone_twitter.ui.home.bottomnavigation.BottomNavigationScreens
import com.morningnightdream.clone_twitter.ui.home.feeds.data.TweetsViewModel
import com.morningnightdream.clone_twitter.ui.search.SearchScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TwitterNavigationHost(
    navController: NavHostController,
    padding: PaddingValues,
    shouldShowAppBar: (Boolean) -> Unit,
    navAction: MainActions,
    shouldShowSearchBar: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Home.route
    ) {
        bottomTabs(navAction, padding, shouldShowSearchBar)
    }
}

private fun NavGraphBuilder.bottomTabs(
    actions: MainActions,
    padding: PaddingValues,
    shouldShowSearchBar: (Boolean) -> Unit
) {
    composable(BottomNavigationScreens.Home.route) { backStack ->
        shouldShowSearchBar(false)
        val tweetsViewModel: TweetsViewModel = hiltViewModel()
        HomeScreen(
            navigateToTweet = { tweetId ->
                actions.navigateToTweet(tweetId, backStack)
            },
            modifierPadding = padding,
            navigateToHashTagSearch = { hashTagSearchParam ->
                actions.navigateToSearch(hashTagSearchParam)
            },
            tweetsViewModel = tweetsViewModel
        )

        BackHandler {
            actions.drawerCheck()
        }
    }

    composable("${BottomNavigationScreens.Search.route}/${1+1}") {
        shouldShowSearchBar(true)
        BackHandler {
            actions.drawerCheck()
        }
    }

    composable(BottomNavigationScreens.Search.route) {
        shouldShowSearchBar(true)
        SearchScreen(
            modifierPadding = padding,
            hashTagParams = it.arguments?.getString(DestinationsArguments.HASH_TAG_KEY))
        BackHandler {
            actions.drawerCheck()
        }
    }

    composable(BottomNavigationScreens.Notifications.route) {
        shouldShowSearchBar(false)
        Text(text = "notification")
        BackHandler {
            actions.drawerCheck()
        }
    }

    composable(BottomNavigationScreens.Message.route) {
        shouldShowSearchBar(false)

        Text(text = "message")

        BackHandler {
            actions.drawerCheck()
        }
    }
}


object DestinationsArguments {
    const val TWEET_ID_KEY = "tweetId"
    const val HASH_TAG_KEY = "hashTagParam"
}

class MainActions(
    private val navController: NavHostController,
    private val shouldShowAppBar: (Boolean) -> Unit,
    private val scaffoldState: ScaffoldState,
    private val scope: CoroutineScope,
    private val shouldSHowSearchBar: (Boolean) -> Unit
) {
    fun drawerCheck() {
        if (scaffoldState.drawerState.isOpen) {
            scope.launch {
                scaffoldState.drawerState.close()
            }
        } else {
            navController.navigateUp()
        }
    }

    val navigateToTweet = { tweetId: String, from: NavBackStackEntry ->
        if (from.lifecycleIsResumed()) {
            shouldShowAppBar(false)
            navController.navigate(route = "${1 + 1}")
        }
    }

    fun navigateToSearch(hashTag: String, navigateHomeFirst: Boolean = false) {
        shouldSHowSearchBar(true)
        if (navigateHomeFirst) {
            while(!navController.navigateUp()) {
                navController.popBackStack()
            }
            shouldShowAppBar(true)
            navController.navigate(route = "${1 + 1}") {
                popUpTo(navController.graph.startDestinationId)
            }
        } else {
            navController.navigate(route = "${1 + 1}") {
                popUpTo(navController.graph.startDestinationId)
            }
        }
    }

    val switchBottomTab = { tabRoute: String, currentRoute: String ->
        if (tabRoute != currentRoute) {
            navController.navigate(route = tabRoute) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    fun upPress(from: NavBackStackEntry, shouldShowAppBar: (Boolean) -> Unit) {
        if (from.lifecycleIsResumed()) {
            shouldShowAppBar(true)
            navController.navigateUp()
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed(): Boolean {
    return this.lifecycle.currentState == Lifecycle.State.RESUMED
}