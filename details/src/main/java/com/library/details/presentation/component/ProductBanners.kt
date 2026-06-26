package com.library.details.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.library.core.ui.theme.Tajawal
import com.library.details.presentation.contract.ProductAvailability
import com.library.details.presentation.contract.ProductDetails

@Composable
fun ProductExtraSection(product: ProductDetails, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
    ) {
        AvailabilityChip(availability = product.availability)

        OfferBanner(offer = product.offer)
    }
}


@Composable
fun AvailabilityChip(availability: ProductAvailability, modifier: Modifier = Modifier) {
    if (availability == ProductAvailability.UNKNOWN) return

    val (containerColor, contentColor, icon, text) = when (availability) {
        ProductAvailability.AVAILABLE -> Quadruple(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
            MaterialTheme.colorScheme.primary,
            Icons.Default.CheckCircle,
            "متوفر"
        )
        ProductAvailability.OUT_OF_STOCK -> Quadruple(
            MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f),
            MaterialTheme.colorScheme.error,
            Icons.Default.Error,
            "غير متوفر"
        )
        else -> return
    }

    Surface(
        color = containerColor,
        modifier = modifier
            .fillMaxWidth().padding(vertical = 16.dp)
            .animateContentSize(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(

            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = contentColor
            )
            Text(
                text = text,

                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = Tajawal,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )
            )
        }
    }
}

@Composable
fun OfferBanner(offer: String?, modifier: Modifier = Modifier) {
    AnimatedVisibility(visible = !offer.isNullOrEmpty()) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .animateContentSize(),
            color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    Icons.Default.LocalOffer,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = offer ?: "",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = Tajawal,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                )
            }
        }
    }
}

private data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
