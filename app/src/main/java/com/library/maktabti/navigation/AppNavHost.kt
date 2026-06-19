package com.library.maktabti.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    innerPadding: PaddingValues,
//    mainViewModel: MainViewModel
) {
//    val isOnline by mainViewModel.isOnline.observeAsState(initial = true)
    NavHost(
        navController = navController,
        startDestination = AppRoute.SplashRoute
    ) {

//        composable<AppRoute.SplashRoute> {
//            val viewModel = hiltViewModel<SplashViewModel>()
//            SplashNavHandler(navController = navController, viewModel = viewModel)
//            SplashScreen(modifier = modifier.padding(innerPadding), viewModel = viewModel)
//        }


    }
}