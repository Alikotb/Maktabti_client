package com.library.maktabti.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: AppRoute,
    val selectedIcon: ImageVector,
    val label: String
) {
    object Home : BottomNavItem(
        route = AppRoute.HomeRoute,
        selectedIcon = Icons.Outlined.Home,
        label = "الرئيسية"
    )

    object Sections : BottomNavItem(
        route = AppRoute.SectionsRoute,
        selectedIcon = Icons.Outlined.GridView,
        label = "الأقسام"
    )

    object Favorite : BottomNavItem(
        route = AppRoute.FavoriteRoute,
        selectedIcon = Icons.Outlined.FavoriteBorder,
        label = "المفضلة"
    )

    object About : BottomNavItem(
        route = AppRoute.AboutRoute,
        selectedIcon = Icons.Outlined.Info,
        label = "عن المكتبة"
    )

    companion object {
        val items = listOf(Home, Sections, Favorite, About)
    }
}
