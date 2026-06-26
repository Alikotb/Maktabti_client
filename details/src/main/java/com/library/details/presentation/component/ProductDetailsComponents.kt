package com.library.details.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.core.ui.theme.Tajawal
import com.library.details.presentation.contract.ProductAvailability

@Composable
fun ProductHeroSection(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProductStatusBadges(
    isNew: Boolean,
    isSpecial: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (isNew) {
            BadgeChip(text = "جديد", containerColor = MaterialTheme.colorScheme.primaryContainer)
        }
        if (isSpecial) {
            BadgeChip(text = "مميز", containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        }
    }
}

@Composable
private fun BadgeChip(text: String, containerColor: Color) {
    Surface(
        color = containerColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelLarge.copy(
                fontFamily = Tajawal,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun ProductInfoSection(
    name: String,
    description: String,
    category: String?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontFamily = Tajawal,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        category?.let {
            AssistChip(
                onClick = { },
                label = { Text(text = it, fontFamily = Tajawal) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Category,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            )
        }

        var isExpanded by remember { mutableStateOf(false) }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(
                    alpha = 0.3f
                )
            ),
            shape = RoundedCornerShape(16.dp),
            onClick = { isExpanded = !isExpanded }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "التفاصيل",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = Tajawal,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = Tajawal,
                        lineHeight = 24.sp
                    ),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun ProductAvailabilityCard(
    availability: ProductAvailability,
    modifier: Modifier = Modifier
) {
    if (availability == ProductAvailability.UNKNOWN) return

    val (color, icon, text) = when (availability) {
        ProductAvailability.AVAILABLE -> Triple(
            MaterialTheme.colorScheme.primary,
            Icons.Default.CheckCircle,
            "متوفر في المخزن"
        )

        ProductAvailability.OUT_OF_STOCK -> Triple(
            MaterialTheme.colorScheme.error,
            Icons.Default.Error,
            "غير متوفر حالياً"
        )

        else -> return
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(icon, contentDescription = null, tint = color)
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = Tajawal,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            )
        }
    }
}



@Composable
fun ProductOfferCard(
    offer: String?,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = !offer.isNullOrEmpty()) {
        Card(
            modifier = modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
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
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = offer ?: "",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = Tajawal,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )
            }
        }
    }
}

@Composable
fun ProductLoadingState() {
    val infiniteTransition = rememberInfiniteTransition()
    val xOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Restart
        )
    )

    val shimmerBrush = Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        ),
        start = Offset(xOffset - 1000f, 0f),
        end = Offset(xOffset, 0f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(32.dp))
                .background(shimmerBrush)
        )
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(shimmerBrush)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(shimmerBrush)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(shimmerBrush)
        )
    }
}

@Composable
fun ProductErrorState(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.CloudOff,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "عذراً، حدث خطأ ما",
            style = MaterialTheme.typography.headlineSmall.copy(fontFamily = Tajawal)
        )
        Text(
            text = "يرجى التحقق من اتصالك بالإنترنت والمحاولة مرة أخرى",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = Tajawal),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRetry) {
            Text("إعادة المحاولة", fontFamily = Tajawal)
        }
    }
}

@Composable
fun ProductEmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.Inventory2,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "المنتج غير موجود",
            style = MaterialTheme.typography.headlineSmall.copy(fontFamily = Tajawal)
        )
    }
}
