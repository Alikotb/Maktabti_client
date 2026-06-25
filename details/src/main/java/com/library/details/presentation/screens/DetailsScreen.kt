package com.library.details.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.library.core.ui.theme.Tajawal
import com.library.details.presentation.component.ProductAvailabilityCard
import com.library.details.presentation.component.ProductEmptyState
import com.library.details.presentation.component.ProductErrorState
import com.library.details.presentation.component.ProductHeroSection
import com.library.details.presentation.component.ProductInfoSection
import com.library.details.presentation.component.ProductLoadingState
import com.library.details.presentation.component.ProductOfferCard
import com.library.details.presentation.component.ProductPriceCard
import com.library.details.presentation.component.ProductStatusBadges
import com.library.details.presentation.contract.ProductDetails
import com.library.details.presentation.contract.ProductDetailsIntent
import com.library.details.presentation.view_model.ProductDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    productId: String,
    onBackClick: () -> Unit,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    LaunchedEffect(productId) {
        viewModel.onIntent(ProductDetailsIntent.LoadProduct(productId))
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DetailsTopAppBar(
                isFavorite = state.product?.isFavorite ?: false,
                onBackClick = onBackClick,
                onFavoriteClick = { viewModel.onIntent(ProductDetailsIntent.ToggleFavorite) },
                onShareClick = { viewModel.onIntent(ProductDetailsIntent.ShareProduct) },
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = WindowInsets.systemBars
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> ProductLoadingState()
                state.error != null -> ProductErrorState(onRetry = { viewModel.onIntent(ProductDetailsIntent.Retry) })
                state.isEmpty -> ProductEmptyState()
                state.product != null -> {
                    ProductDetailsAdaptiveContent(
                        product = state.product!!,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsTopAppBar(
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    LargeTopAppBar(
        title = {
            Text(
                text = "تفاصيل المنتج",
                fontFamily = Tajawal,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = onShareClick) {
                Icon(Icons.Default.Share, contentDescription = "Share")
            }
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
private fun ProductDetailsAdaptiveContent(
    product: ProductDetails,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val isTablet = maxWidth > 600.dp
        
        if (isTablet) {
            // Two-pane layout for tablets
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ProductHeroSection(imageRes = product.image)
                }
                Column(
                    modifier = Modifier
                        .weight(1.2f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    ProductMainInfo(product)
                }
            }
        } else {
            // Single column layout for phones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                ProductHeroSection(imageRes = product.image)
                ProductMainInfo(product)
            }
        }
    }
}

@Composable
private fun ColumnScope.ProductMainInfo(product: ProductDetails) {
    ProductStatusBadges(isNew = product.isNew, isSpecial = product.isSpecial)
    
    ProductInfoSection(
        name = product.name,
        description = product.description,
        category = product.category
    )
    
    ProductAvailabilityCard(availability = product.availability)
    
    ProductOfferCard(offer = product.offer)
    
    Spacer(modifier = Modifier.weight(1f))
    
    ProductPriceCard(
        price = product.price,
        currency = product.currency
    )
}
