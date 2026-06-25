package com.library.maktabti.navigation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.library.home.presentation.contract.HomeContract
import com.library.home.presentation.view_model.HomeViewModel
import com.library.maktabti.navigation.AppRoute

@Composable
fun HomeNavHandler(
    navController: NavHostController,
    viewModel: HomeViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                HomeContract.Effect.NavigateToSearch -> {
//                    navController.popBackStack()
                    navController.navigate(AppRoute.SearchRoute)
                }
            }
        }
    }
}