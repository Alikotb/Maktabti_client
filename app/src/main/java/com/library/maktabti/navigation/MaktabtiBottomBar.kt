package com.library.maktabti.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import com.library.core.ui.theme.Tajawal

@Composable
fun MaktabtiBottomBar(
    currentRoute: String?,
    onTabSelected: (BottomNavItem) -> Unit
) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 56.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(100.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(24.dp)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
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
                            modifier = Modifier.padding(top = 4.dp),
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontFamily = Tajawal,
                                )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                    ),

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