package com.library.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.core.ui.theme.Tajawal
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
            FeaturedProductsCard(
                product = product,
                onProductClick = onProductClick,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Composable
fun FeaturedProductsCard(
    modifier: Modifier = Modifier,
    product: Product,
    onProductClick: (Product) -> Unit = {},
    onFavoriteClick: (Product) -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(190.dp)
            .clickable { onProductClick(product) },
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Favorite Button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 8.dp, top = 8.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable { onFavoriteClick(product) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (product.isFavorite)
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        tint = if (product.isFavorite)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
                        modifier = Modifier.size(18.dp)
                    )
                }

                // New Badge
                if (product.isNew) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "جديد",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = Tajawal,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 14.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = product.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Tajawal,
                        color = MaterialTheme.colorScheme.secondary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = product.price.toInt().toString(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Tajawal,
                        color = MaterialTheme.colorScheme.primary
                    )
                )

            }
        }
    }
}

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val image: Int,
    val isNew: Boolean = false,
    val isFavorite: Boolean = false
)

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
    )
)
