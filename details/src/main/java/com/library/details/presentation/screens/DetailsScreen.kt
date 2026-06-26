package com.library.details.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.library.details.presentation.component.DescriptionSection
import com.library.details.presentation.component.ProductEmptyState
import com.library.details.presentation.component.ProductErrorState
import com.library.details.presentation.component.ProductExtraSection
import com.library.details.presentation.component.ProductHeroSection
import com.library.details.presentation.component.ProductInformation
import com.library.details.presentation.component.ProductLoadingState
import com.library.details.presentation.contract.ProductDetailsContract
import com.library.details.presentation.view_model.ProductDetailsViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun DetailsScreen(
    productId: String = "1",
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(productId) {
        viewModel.onIntent(ProductDetailsContract.Intent.LoadProduct(productId))
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val maxWidth = maxWidth
        val isLargeScreen = maxWidth > 700.dp
        val contentWidth = if (isLargeScreen) 700.dp else maxWidth

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            when {
                state.isLoading -> ProductLoadingState()
                state.error != null -> ProductErrorState(onRetry = {
                    viewModel.onIntent(ProductDetailsContract.Intent.Retry)
                })
                state.isEmpty || state.product == null -> ProductEmptyState{
                    viewModel.onIntent(ProductDetailsContract.Intent.BackClicked)
                }
                else -> {
                    val product = state.product!!
                    val configuration = LocalConfiguration.current
                    val screenHeight = configuration.screenHeightDp.dp

                    LazyColumn(
                        modifier = Modifier
                            .width(contentWidth)
                            .fillMaxSize()
                    ) {
                        item {
                            ProductHeroSection(
                                product = product,
                                screenHeight = screenHeight,
                                onBackClick = { viewModel.onIntent(ProductDetailsContract.Intent.BackClicked) },
                                onFavoriteClick = { viewModel.onIntent(ProductDetailsContract.Intent.ToggleFavorite) },
                                onShareClick = { viewModel.onIntent(ProductDetailsContract.Intent.ShareProduct) }
                            )
                        }

                        item {
                            ProductInformation(product = product)
                        }

                        item {
                            DescriptionSection(description = product.description)
                        }

                        item {
                            ProductExtraSection(product = product)
                        }

                        item {
                            Spacer(
                                modifier = Modifier.height(
                                    WindowInsets.navigationBars.asPaddingValues()
                                        .calculateBottomPadding() + 32.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
