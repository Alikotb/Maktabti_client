package com.library.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.core.R
import com.library.core.ui.theme.Tajawal

//
//@Composable
//fun ProductsCard(
//    modifier: Modifier = Modifier,
//    product: Product,
//    onProductClick: (Product) -> Unit = {},
//    onFavoriteClick: (Product) -> Unit = {}
//) {
//    Card(
//        modifier = modifier
//            .width(190.dp)
//            .clickable { onProductClick(product) },
//        shape = RoundedCornerShape(28.dp),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Column {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(190.dp)
//                    .background(MaterialTheme.colorScheme.background)
//            ) {
//                Image(
//                    painter = painterResource(id = product.image),
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxSize(),
//                    contentScale = ContentScale.Crop
//                )
//
//                // Favorite Button
//                Box(
//                    modifier = Modifier
//                        .align(Alignment.TopEnd)
//                        .padding(end = 8.dp, top = 8.dp)
//                        .size(40.dp)
//                        .clip(CircleShape)
//                        .background(MaterialTheme.colorScheme.surface)
//                        .clickable { onFavoriteClick(product) },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        imageVector = if (product.isFavorite)
//                            Icons.Filled.Favorite
//                        else
//                            Icons.Outlined.FavoriteBorder,
//                        contentDescription = null,
//                        tint = if (product.isFavorite)
//                            MaterialTheme.colorScheme.primary
//                        else
//                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
//                        modifier = Modifier.size(18.dp)
//                    )
//                }
//
//                // New Badge
//                if (product.isNew) {
//                    Box(
//                        modifier = Modifier
//                            .align(Alignment.TopStart)
//                            .padding(12.dp)
//                            .background(
//                                color = MaterialTheme.colorScheme.onPrimary,
//                                shape = RoundedCornerShape(12.dp)
//                            )
//                            .padding(horizontal = 12.dp, vertical = 6.dp)
//                    ) {
//                        Text(
//                            text = "جديد",
//                            style = TextStyle(
//                                fontSize = 11.sp,
//                                fontWeight = FontWeight.ExtraBold,
//                                fontFamily = Tajawal,
//                                color = MaterialTheme.colorScheme.secondary
//                            )
//                        )
//                    }
//                }
//            }
//
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 12.dp, vertical = 14.dp),
//                horizontalAlignment = Alignment.Start
//            ) {
//                Text(
//                    text = product.name,
//                    style = TextStyle(
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = Tajawal,
//                        color = MaterialTheme.colorScheme.secondary
//                    ),
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis,
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(6.dp))
//
//                Text(
//                    text = product.price.toInt().toString(),
//                    style = TextStyle(
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = Tajawal,
//                        color = MaterialTheme.colorScheme.primary
//                    )
//                )
//
//            }
//        }
//    }
//}


@Composable
fun ProductsCard(
    product: Product,
    modifier: Modifier = Modifier,
    size: ProductCardSize = ProductCardSize.Medium,
    onProductClick: (Product) -> Unit = {},
    onFavoriteClick: (Product) -> Unit = {}
) {

    val imageHeight = when (size) {
        ProductCardSize.Small -> 130.dp
        ProductCardSize.Medium -> 190.dp
        ProductCardSize.Large -> 260.dp
    }

    val favoriteButtonSize = when (size) {
        ProductCardSize.Small -> 32.dp
        ProductCardSize.Medium -> 40.dp
        ProductCardSize.Large -> 48.dp
    }

    val titleFontSize = when (size) {
        ProductCardSize.Small -> 12.sp
        ProductCardSize.Medium -> 14.sp
        ProductCardSize.Large -> 16.sp
    }

    val priceFontSize = when (size) {
        ProductCardSize.Small -> 14.sp
        ProductCardSize.Medium -> 18.sp
        ProductCardSize.Large -> 22.sp
    }

    Card(
        modifier = modifier.clickable {
            onProductClick(product)
        },
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .background(MaterialTheme.colorScheme.background)
            ) {

                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                CircleIconButton(
                    imageVector = if (product.isFavorite)
                        Icons.Filled.Favorite
                    else
                        Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    size = favoriteButtonSize,
                    iconSizeRatio = 0.45f,
                    iconTint = if (product.isFavorite)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    onClick = {
                        onFavoriteClick(product)
                    }
                )

                if (product.isNew) {

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(
                                horizontal = 12.dp,
                                vertical = 6.dp
                            )
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
                    .padding(
                        horizontal = 12.dp,
                        vertical = 14.dp
                    )
            ) {

                Text(
                    text = product.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = titleFontSize,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Tajawal,
                        color = MaterialTheme.colorScheme.secondary
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "${product.price.toInt()} ج.م",
                    style = TextStyle(
                        fontSize = priceFontSize,
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

enum class ProductCardSize {
    Small,
    Medium,
    Large
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