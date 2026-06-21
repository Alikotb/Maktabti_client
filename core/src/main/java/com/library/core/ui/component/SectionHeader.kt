package com.library.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.library.core.ui.theme.Tajawal

@Composable
fun SectionHeader(modifier: Modifier = Modifier,sectionName: String="الأقسام",onClick: () -> Unit={}) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            sectionName,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = Tajawal,
                color = MaterialTheme.colorScheme.secondary
            )
        )
        Text(
            "عرض الكل",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,

            ),
            modifier = Modifier.clickable(onClick = onClick),
            textDecoration = TextDecoration.Underline

        )
    }
}