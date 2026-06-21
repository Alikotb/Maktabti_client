package com.library.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.core.ui.theme.Tajawal
import com.library.home.R

@Composable
fun CategoriesSection(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
    ){
        items(categories){
            CategoriesCard(photo = it.icon, name = it.name)
        }
    }

}


@Composable
fun CategoriesCard(modifier: Modifier = Modifier, photo: Int, name: String) {
    Column(
        modifier = modifier
            .padding(end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(64.dp)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(24.dp),
                    ambientColor = Color.Black.copy(alpha = 0.5f),
                    spotColor = Color.Black.copy(alpha = 0.6f)
                )
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                    RoundedCornerShape(24.dp)
                ),
            contentAlignment =  Alignment.Center

            ) {
            Image(
                painter = painterResource(id = photo),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Fit

            )

        }
        Spacer(Modifier.height(8.dp))
        Text(
            name,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = Tajawal,
                color = MaterialTheme.colorScheme.secondary
            )
        )
    }

}


data class Category(
    val id: String,
    val name: String,
    val icon: Int,
)

val categories = listOf(
    Category(
        id = "notebooks",
        name = "دفاتر ومذكرات",
        icon = R.drawable.notebook ,
    ),
    Category(
        id = "pens",
        name = "أقلام فاخرة",
        icon =  R.drawable.pencile,
    ),
    Category(
        id = "art",
        name = "أدوات الرسم",
        icon =  R.drawable.notebook,
    ),
    Category(
        id = "notebooks",
        name = "دفاتر ومذكرات",
        icon = R.drawable.notebook ,
    ),
    Category(
        id = "pens",
        name = "أقلام فاخرة",
        icon =  R.drawable.pencile,
    ),
    Category(
        id = "art",
        name = "أدوات الرسم",
        icon =  R.drawable.notebook,
    ),
    Category(
        id = "notebooks",
        name = "دفاتر ومذكرات",
        icon = R.drawable.notebook ,
    ),
    Category(
        id = "pens",
        name = "أقلام فاخرة",
        icon =  R.drawable.pencile,
    ),
    Category(
        id = "art",
        name = "أدوات الرسم",
        icon =  R.drawable.notebook,
    ),

)