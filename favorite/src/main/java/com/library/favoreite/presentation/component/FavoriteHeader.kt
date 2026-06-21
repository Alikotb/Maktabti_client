package com.library.favoreite.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.core.ui.theme.Tajawal

@Composable
fun FavoriteHeader(modifier: Modifier = Modifier, numberOfProduct: Int = 0) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            "المفضلة", style = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = Tajawal
            ),
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "$numberOfProduct منتج محفوظ", style = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = Tajawal
            ),
            textAlign = TextAlign.Start
        )
    }

}