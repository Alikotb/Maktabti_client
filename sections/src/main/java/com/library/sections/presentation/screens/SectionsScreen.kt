package com.library.sections.presentation.screens

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
import com.library.core.ui.component.CategoriesSection
import com.library.core.ui.component.ProductCardSize
import com.library.core.ui.component.ProductsCard
import com.library.core.ui.component.featuredProducts
import com.library.sections.presentation.component.CategoryHeader

@Composable
fun SectionsScreen(
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 8.dp),

        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            CategoryHeader()
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            CategoriesSection()
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(4.dp))
        }
        items(featuredProducts) { product ->
            ProductsCard(
                product = product,
                size = ProductCardSize.Small,
                modifier = Modifier.width(190.dp),
                onProductClick = {

                },
                onFavoriteClick = {

                }
            )

        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(160.dp))
        }

    }

}
