package com.library.details.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.core.ui.theme.Tajawal
import com.library.details.presentation.contract.ProductDetails

@Composable
fun ProductInformation(
    product: ProductDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp).offset(y = (-8).dp),
    ) {

        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontFamily = Tajawal,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.secondary,
                lineHeight = 34.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )

    }
}



@Composable
fun DescriptionSection(description: String, modifier: Modifier = Modifier) {
    if (description.isEmpty()) return
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "الوصف",
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = Tajawal,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 24.sp
            )
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = Tajawal,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Normal,

                )
        )
    }
}
