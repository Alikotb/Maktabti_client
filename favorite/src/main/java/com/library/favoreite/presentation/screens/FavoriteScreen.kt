package com.library.favoreite.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.library.core.ui.component.ProductCardSize
import com.library.core.ui.component.ProductsCard
import com.library.core.ui.component.featuredProducts
import com.library.favoreite.presentation.component.FavoriteHeader
import com.library.favoreite.presentation.contract.FavoriteContract
import com.library.favoreite.presentation.view_model.FavoriteViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 10.dp),

        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(24.dp))
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            FavoriteHeader(modifier = Modifier.padding(start = 10.dp), numberOfProduct = 3)
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(2.dp))
        }
        items(featuredProducts) { product ->
            ProductsCard(
                product = product,
                size = ProductCardSize.Medium,
                modifier = Modifier.width(190.dp),
                onProductClick = {
                    viewModel.onIntent(FavoriteContract.Intent.NavigateToDetails(product.id))
                },
                onFavoriteClick = {
                    viewModel.onIntent(FavoriteContract.Intent.NavigateToDetails(product.id))

                }
            )

        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(160.dp))
        }
    }
}
