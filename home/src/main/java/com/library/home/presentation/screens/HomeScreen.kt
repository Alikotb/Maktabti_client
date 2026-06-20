package com.library.home.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.library.core.ui.theme.Tajawal

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
//            modifier = modifier.fillMaxSize()
//                .background(MaterialTheme.colorScheme.background),
////        contentAlignment = Alignment.Center
        ) {
            items(100) {
                Text(text = "item $it", color = Color.Black)
            }
        }
    }
//    Box(
//        modifier = modifier.fillMaxSize()
//            .background(MaterialTheme.colorScheme.background),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "Home Screen",
//            fontSize = 36.sp,
//            fontFamily = Tajawal,
//            fontWeight = FontWeight.ExtraBold,
//            color = MaterialTheme.colorScheme.secondary,
//            textAlign = TextAlign.Center
//        )
//
//    }
}