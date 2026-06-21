package com.library.home.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.library.core.ui.component.SectionHeader
import com.library.home.presentation.component.CategoriesSection
import com.library.home.presentation.component.FeaturedProductsSection
import com.library.home.presentation.component.NewProductSection
import com.library.home.presentation.component.OfferSection
import com.library.home.presentation.component.SearchSection

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(MaterialTheme.colorScheme.background),
    ) {
        LazyColumn {
            item {
                SearchSection()
            }
            item {
                OfferSection()
            }
            item {
                Spacer(Modifier.height(6.dp))
                SectionHeader()
                Spacer(Modifier.height(8.dp))
                CategoriesSection()
            }
            item {
                Spacer(Modifier.height(26.dp))
                SectionHeader(sectionName = "المنتجات المميزة")
                Spacer(Modifier.height(12.dp))
                FeaturedProductsSection()
            }
            item {
                Spacer(Modifier.height(32.dp))
                SectionHeader(sectionName = "وصل حديثاً")
                Spacer(Modifier.height(12.dp))
                NewProductSection()
            }
            item {
                Spacer(Modifier.height(200.dp))
            }
        }
    }

}