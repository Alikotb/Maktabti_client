package com.library.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.library.core.ui.component.Product
import com.library.core.ui.component.ProductCardSize
import com.library.core.ui.component.ProductsCard
import com.library.home.R

@Composable
fun FeaturedProductsSection(
    modifier: Modifier = Modifier,
    products: List<Product> = featuredProducts,
    onProductClick: (Product) -> Unit = {},
    onFavoriteClick: (Product) -> Unit = {}
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products) { product ->
//            ProductsCard(
//                product = product,
//                onProductClick = onProductClick,
//                onFavoriteClick = onFavoriteClick
//            )
            ProductsCard(
                product = product,
                size = ProductCardSize.Medium,
                modifier = Modifier.width(190.dp),
                onProductClick ={
                    onProductClick(product)
                } ,
                onFavoriteClick ={
                    onFavoriteClick(product)
                }
            )
        }
    }
}


val featuredProducts = listOf(
    Product(
        id = "1",
        name = "حقيبة قماشية كلاسيكية",
        price = 230.0,
        image = R.drawable.p_bag,
        isFavorite = false
    ),
    Product(
        id = "2",
        name = "قلم حبر خشبي نحاسي",
        price = 145.0,
        image = R.drawable.p_pen,
        isNew = true,
        isFavorite = true
    ),
    Product(
        id = "3",
        name = "دفتر ملاحظات جلدي",
        price = 85.0,
        image = R.drawable.p_books,
        isFavorite = false
    ),
    Product(
        id = "1",
        name = "حقيبة قماشية كلاسيكية",
        price = 230.0,
        image = R.drawable.p_bag,
        isFavorite = false
    ),
)
