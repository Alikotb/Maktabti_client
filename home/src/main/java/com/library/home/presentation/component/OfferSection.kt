package com.library.home.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.core.ui.theme.LightMainColor
import com.library.core.ui.theme.Tajawal
import com.library.home.R
import kotlinx.coroutines.delay

data class Offer(
    val title: String,
    val subTitle: String,
    val badge: String,
    val imageRes: Int
)

@Composable
fun OfferSection(modifier: Modifier = Modifier) {
    val offers = listOf(
        Offer(
            title = "خصم 25% على المذكرات الجلدية",
            subTitle = "حتى نهاية الأسبوع فقط",
            badge = "عرض خاص",
            imageRes = R.drawable.banner
        ),
        Offer(
            title = "خصم 25% على المذكرات الجلدية",
            subTitle = "حتى نهاية الأسبوع فقط",
            badge = "عرض خاص",
            imageRes = R.drawable.banner
        ),
        Offer(
            title = "خصم 25% على \nالمذكرات الجلدية",
            subTitle = "حتى نهاية الأسبوع فقط",
            badge = "عرض خاص",
            imageRes = R.drawable.banner
        )
    )

    val pagerState = rememberPagerState(pageCount = { offers.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)

            val nextPage =
                (pagerState.currentPage + 1) % pagerState.pageCount

            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
        ) { page ->

            Box(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                OfferCard(offers[page])
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(offers.size) { iteration ->
                val isSelected = pagerState.currentPage == iteration
                val dotWidth by animateDpAsState(
                    targetValue = if (isSelected) 32.dp else 10.dp,
                    label = "dotWidth"
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) LightMainColor else Color(0xFFD7CCC8)
                        )
                        .size(width = dotWidth, height = 10.dp)
                )
            }
        }
    }
}

@Composable
fun OfferCard(offer: Offer) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(24.dp),

        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = offer.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f),
                                MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
                            )
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp)
                    .align(Alignment.TopStart)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = offer.badge,
                    color = Color.White,
                    fontFamily = Tajawal,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 8.dp, start = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = offer.title,
                    maxLines = 2,
                    color = Color.White,
                    fontFamily = Tajawal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    lineHeight = 30.sp
                )
                Text(
                    text = offer.subTitle,
                    color = Color.White.copy(alpha = 0.8f),
                    fontFamily = Tajawal,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

