package com.library.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.library.core.ui.component.Product
import com.library.core.ui.component.ProductCardSize
import com.library.core.ui.component.ProductsCard
import com.library.core.ui.component.featuredProducts

@Composable
fun NewProductSection(
    products: List<Product> = featuredProducts.take(4),
    onProductClick: (Product) -> Unit = {},
    onFavoriteClick: (Product) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        products.chunked(2).forEach { rowProducts ->

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                rowProducts.forEach { product ->

                    ProductsCard(
                        product = product,
                        size = ProductCardSize.Medium,
                        modifier = Modifier.weight(1f),
                        onProductClick = onProductClick,
                        onFavoriteClick = onFavoriteClick
                    )
                }

                if (rowProducts.size == 1) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}