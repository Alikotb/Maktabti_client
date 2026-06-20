package com.library.maktabti.navigation

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination

@Composable
fun MaktabtiBottomBar(
    currentRoute: String?,
    onTabSelected: (BottomNavItem) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .padding(bottom = 48.dp)
            .clip(RoundedCornerShape(32.dp)),
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            BottomNavItem.items.forEach { item ->
                val isSelected =
                    currentRoute == item.route::class.qualifiedName
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            onTabSelected(item)
                        }
                    },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (item is BottomNavItem.Favorite) {
                                    Badge(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = Color.White,
                                        modifier = Modifier.offset(x = (-4).dp, y = 4.dp)
                                    ) {
                                        Text("4")
                                    }
                                }
                            }
                        ) {
                            if (isSelected)
                                Icon(
                                    imageVector = item.selectedIcon,
                                    contentDescription = item.label,
                                    modifier = Modifier.size(30.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            else
                                Icon(
                                    imageVector = item.selectedIcon,
                                    contentDescription = item.label,
                                    modifier = Modifier.size(30.dp),
                                    tint = Color.Gray
                                )
                        }
                    },
                    label = {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal
                            )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    )
                )
            }
        }
    }
}

fun NavDestination?.isBottomBarDestination(): Boolean {
    return BottomNavItem.items.any {
        it.route::class.qualifiedName == this?.route
    }
}