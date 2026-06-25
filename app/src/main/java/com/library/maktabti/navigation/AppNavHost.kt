package com.library.maktabti.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.library.about.presentation.screens.AboutScreen
import com.library.details.presentation.screens.DetailsScreen
import com.library.favoreite.presentation.screens.FavoriteScreen
import com.library.home.presentation.screens.HomeScreen
import com.library.home.presentation.screens.SplashScreen
import com.library.home.presentation.view_model.HomeViewModel
import com.library.home.presentation.view_model.SplashViewModel
import com.library.maktabti.navigation.home.HomeNavHandler
import com.library.maktabti.navigation.home.SplashNavHandler
import com.library.search.presentation.screens.SearchScreen
import com.library.sections.presentation.screens.SectionsScreen


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
        startDestination = AppRoute.HomeRoute
    ) {

        composable<AppRoute.SplashRoute> {
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashNavHandler(navController = navController, viewModel = viewModel)
            SplashScreen(modifier = modifier.padding(innerPadding), viewModel = viewModel)
        }

        composable<AppRoute.HomeRoute> {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeNavHandler(navController = navController, viewModel = viewModel)
            HomeScreen(modifier = modifier.padding(top = innerPadding.calculateTopPadding()), viewModel = viewModel)
        }
        composable<AppRoute.SectionsRoute> {
//            val viewModel = hiltViewModel<SplashViewModel>()
//            SplashNavHandler(navController = navController, viewModel = viewModel)
            SectionsScreen(modifier = modifier.padding(top = innerPadding.calculateTopPadding()))
        }
        composable<AppRoute.FavoriteRoute> {
//            val viewModel = hiltViewModel<SplashViewModel>()
//            SplashNavHandler(navController = navController, viewModel = viewModel)
            FavoriteScreen(modifier = modifier.padding(top = innerPadding.calculateTopPadding()))
        }
        composable<AppRoute.AboutRoute> {
//            val viewModel = hiltViewModel<SplashViewModel>()
//            SplashNavHandler(navController = navController, viewModel = viewModel)
            AboutScreen(modifier = modifier.padding(top = innerPadding.calculateTopPadding()))
        }
        composable<AppRoute.SearchRoute> {
//            val viewModel = hiltViewModel<SplashViewModel>()
//            SplashNavHandler(navController = navController, viewModel = viewModel)
            SearchScreen(modifier = modifier.padding(innerPadding))
        }
        composable<AppRoute.DetailsRoute> {
//            val viewModel = hiltViewModel<SplashViewModel>()
//            SplashNavHandler(navController = navController, viewModel = viewModel)
            DetailsScreen(modifier = modifier.padding(innerPadding))
        }


    }


}