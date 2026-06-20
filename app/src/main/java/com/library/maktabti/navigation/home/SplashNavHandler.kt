package com.library.maktabti.navigation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.library.home.presentation.contract.SplashContract
import com.library.home.presentation.view_model.SplashViewModel
import com.library.maktabti.navigation.AppRoute

@Composable
fun SplashNavHandler(
    navController: NavHostController,
    viewModel: SplashViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SplashContract.Effect.NavigateToHome -> {
                    navController.popBackStack()
                    navController.navigate(AppRoute.HomeRoute)
                }
            }
        }
    }
}