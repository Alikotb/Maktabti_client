package com.library.sections.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
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
import com.library.core.ui.component.CircleIconButton
import com.library.core.ui.theme.Tajawal

@Composable
fun CategoryHeader(modifier: Modifier = Modifier,onBackClick:()->Unit,onClick:()->Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleIconButton(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = "Favorite",
            modifier = Modifier.padding(8.dp),
            onClick = onBackClick
        )
        Text(
            "الأقسام",
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Tajawal
            ),
            textAlign = TextAlign.Center
        )
        CircleIconButton(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Search",
            modifier = Modifier.padding(8.dp),
            onClick = onClick
        )
    }
}