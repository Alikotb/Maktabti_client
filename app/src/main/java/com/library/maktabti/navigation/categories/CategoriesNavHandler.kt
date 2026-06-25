package com.library.maktabti.navigation.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.library.maktabti.navigation.AppRoute
import com.library.sections.presentation.contract.CategoriesContract
import com.library.sections.presentation.view_model.CategoriesViewModel

@Composable
fun CategoriesNavHandler(
    navController: NavHostController,
    viewModel: CategoriesViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CategoriesContract.Effect.NavigateToDetails -> {
                    navController.navigate(AppRoute.DetailsRoute(effect.productId))
                }

                CategoriesContract.Effect.NavigateToSearch -> {
                    navController.navigate(AppRoute.SearchRoute)
                }

                CategoriesContract.Effect.NavigateBack -> {
                    navController.popBackStack()
                }
            }
        }
    }
}
