package com.library.maktabti.navigation.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.library.details.presentation.contract.ProductDetailsContract
import com.library.details.presentation.view_model.ProductDetailsViewModel
import com.library.favoreite.presentation.contract.FavoriteContract
import com.library.favoreite.presentation.view_model.FavoriteViewModel
import com.library.maktabti.navigation.AppRoute

@Composable
fun DetailsNavHandler(
    navController: NavHostController,
    viewModel: ProductDetailsViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ProductDetailsContract.Effect.NavigateBack -> {
                    navController.popBackStack()
                }

            }
        }
    }
}
