package com.library.maktabti.navigation.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.library.maktabti.navigation.AppRoute
import com.library.search.presentation.screens.SearchEffect
import com.library.search.presentation.screens.SearchViewModel

@Composable
fun SearchNavHandler(
    navController: NavHostController,
    viewModel: SearchViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SearchEffect.NavigateToDetails -> {
                    navController.navigate(AppRoute.DetailsRoute(effect.productId))
                }
            }
        }
    }
}
