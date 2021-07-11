package com.morningnightdream.clone_twitter.ui.home.bottomnavigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.morningnightdream.clone_twitter.ui.components.TweetSurface
import com.morningnightdream.clone_twitter.ui.theme.TwitterTheme


val bottomNavigationItems = listOf(
    BottomNavigationScreens.Home,
    BottomNavigationScreens.Search,
    BottomNavigationScreens.Notifications,
    BottomNavigationScreens.Message
)

@Composable
fun TwitterHomeBottomAppBar(
    switchBottomTab: (String, String) -> Unit,
    navController: NavController,
    shouldShowAppBar: Boolean
) {
    val currentRoute = currentRoute(navController = navController)

    if (shouldShowAppBar) {
        TweetSurface(
            color = TwitterTheme.colors.uiBackground,
            contentColor = TwitterTheme.colors.accent,
            elevation = 8.dp
        ) {
            BottomNavigation(
                backgroundColor = TwitterTheme.colors.uiBackground,
                elevation = 4.dp
            ) {
                bottomNavigationItems.forEach { scren ->
                    BottomNavigationTab(
                        screen = scren,
                        switchBottomTab = switchBottomTab,
                        currentRoute = currentRoute
                    )
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


@Composable
private fun RowScope.BottomNavigationTab(
    screen: BottomNavigationScreens,
    switchBottomTab: (String, String) -> Unit,
    currentRoute: String?
) {
    BottomNavigationItem(
        icon = {
            ComposeBottomNavIconState(currentRoute, screen)
        },
        selected = isSelected(currentRoute, screen),
        alwaysShowLabel = true,
        onClick = {
            currentRoute?.let { switchBottomTab(screen.route, it) }
        }
    )
}

@Composable
private fun isSelected(
    currentRoute: String?,
    screen: BottomNavigationScreens
): Boolean {
    return currentRoute == screen.route || currentRoute?.contains(screen.route) == true
}

@Composable
private fun ComposeBottomNavIconState(
    currentRoute: String?,
    screen: BottomNavigationScreens
) {
   when {
       isSelected(currentRoute = currentRoute, screen = screen) -> {
           Icon(
               painterResource(id = screen.icon),
               contentDescription = null
           )
       }
       else -> {
           Icon(
               painterResource(id = screen.iconStroke),
               contentDescription = null
           )
       }
   }
}