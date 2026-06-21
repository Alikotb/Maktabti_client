package com.library.maktabti.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.library.core.ui.theme.Maktabti_clientTheme
import com.library.maktabti.navigation.AppNavHost
import com.library.maktabti.navigation.AppRoute
import com.library.maktabti.navigation.MaktabtiBottomBar
import com.library.maktabti.navigation.isBottomBarDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            val navController = rememberNavController()

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Rtl
            ) {
                Maktabti_clientTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {

                    val (isBottomBarDestination, currentRoute) = navBarHandelation(navController)

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            if (isBottomBarDestination) {
                                MaktabtiBottomBar(
                                    currentRoute = currentRoute,
                                    onTabSelected = { item ->
                                        navController.navigate(item.route) {
                                            popUpTo(AppRoute.HomeRoute) {
                                                inclusive = false
                                            }
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        }
                    ) { innerPadding ->
                        AppNavHost(
                            navController = navController,
                            innerPadding = innerPadding
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun navBarHandelation(navController: NavHostController): Pair<Boolean, String?> {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val isBottomBarDestination = currentDestination.isBottomBarDestination()
        val isHome =
            currentDestination?.route == AppRoute.HomeRoute::class.qualifiedName
        val currentRoute = currentDestination?.route
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())

        BackHandler(
            enabled = isBottomBarDestination && !isHome
        ) {
            navController.navigate(AppRoute.HomeRoute) {
                popUpTo(AppRoute.HomeRoute) {
                    inclusive = false
                }
                launchSingleTop = true
            }
        }
        return Pair(isBottomBarDestination, currentRoute)
    }
}
