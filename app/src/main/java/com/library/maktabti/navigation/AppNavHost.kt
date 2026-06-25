package com.library.maktabti.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.library.about.presentation.screens.AboutScreen
import com.library.details.presentation.screens.DetailsScreen
import com.library.favoreite.presentation.screens.FavoriteScreen
import com.library.favoreite.presentation.view_model.FavoriteViewModel
import com.library.home.presentation.screens.HomeScreen
import com.library.home.presentation.screens.SplashScreen
import com.library.home.presentation.view_model.HomeViewModel
import com.library.home.presentation.view_model.SplashViewModel
import com.library.maktabti.navigation.categories.CategoriesNavHandler
import com.library.maktabti.navigation.details.FavoriteNavHandler
import com.library.maktabti.navigation.home.HomeNavHandler
import com.library.maktabti.navigation.home.SplashNavHandler
import com.library.maktabti.navigation.search.SearchNavHandler
import com.library.search.presentation.screens.SearchScreen
import com.library.search.presentation.screens.SearchViewModel
import com.library.sections.presentation.screens.SectionsScreen
import com.library.sections.presentation.view_model.CategoriesViewModel


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
            HomeScreen(
                modifier = modifier.padding(top = innerPadding.calculateTopPadding()),
                viewModel = viewModel
            )
        }
        composable<AppRoute.SectionsRoute> {
//
            val viewModel = hiltViewModel<CategoriesViewModel>()
            CategoriesNavHandler(navController = navController, viewModel = viewModel)

            SectionsScreen(modifier = modifier.padding(top = innerPadding.calculateTopPadding()),viewModel = viewModel)
        }
        composable<AppRoute.FavoriteRoute> {
            val viewModel = hiltViewModel<FavoriteViewModel>()
            FavoriteNavHandler(navController = navController, viewModel = viewModel)
            FavoriteScreen(modifier = modifier.padding(top = innerPadding.calculateTopPadding()))
        }
        composable<AppRoute.AboutRoute> {
            AboutScreen(modifier = modifier.padding(top = innerPadding.calculateTopPadding()))
        }
        composable<AppRoute.SearchRoute> {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchNavHandler(navController = navController, viewModel = viewModel)
            SearchScreen(modifier = modifier.padding(innerPadding), viewModel = viewModel)
        }
        composable<AppRoute.DetailsRoute> { backStackEntry ->
            val detailsRoute: AppRoute.DetailsRoute = backStackEntry.toRoute()
            DetailsScreen(
                productId = detailsRoute.productId,
                onBackClick = { navController.popBackStack() },
                modifier = modifier.padding(innerPadding)
            )
        }


    }


}