package com.library.maktabti.navigation.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.library.favoreite.presentation.contract.FavoriteContract
import com.library.favoreite.presentation.view_model.FavoriteViewModel
import com.library.maktabti.navigation.AppRoute

@Composable
fun FavoriteNavHandler(
    navController: NavHostController,
    viewModel: FavoriteViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is FavoriteContract.Effect.NavigateToDetails -> {
                    navController.navigate(AppRoute.DetailsRoute(effect.productId))
                }
            }
        }
    }
}
