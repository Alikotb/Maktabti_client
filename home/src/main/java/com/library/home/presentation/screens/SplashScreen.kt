package com.library.home.presentation.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.core.R
import com.library.core.ui.component.AppButton
import com.library.core.ui.theme.Tajawal
import com.library.home.presentation.contract.SplashContract
import com.library.home.presentation.view_model.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableFloatStateOf(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "logo_animation")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offset_y"
    )

    LaunchedEffect(Unit) {
        while (progress < 1f) {
            progress += 0.03f
            delay(50)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Box(
            modifier = Modifier
                .offset(y = offsetY.dp)
                .size(170.dp)
                .shadow(
                    elevation = 64.dp,
                    shape = RoundedCornerShape(36.dp),
                    ambientColor = MaterialTheme.colorScheme.secondary,
                    spotColor = MaterialTheme.colorScheme.primary
                )
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(144.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            text = "مكتبة الرواق",
            fontSize = 36.sp,
            fontFamily = Tajawal,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "حيث تلتقي الكلمات بالورق",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(64.dp))

        LinearProgressIndicator(
            progress = { progress },
            strokeCap = StrokeCap.Butt,

            modifier = Modifier
                .width(160.dp)
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
        )


        Spacer(modifier = Modifier.height(40.dp))

        AppButton(
            text = "ابدأ التسوق",
            onClick = {
                viewModel.onIntent(SplashContract.Intent.NavigateToNext)
            },
            backgroundColor = MaterialTheme.colorScheme.primary,
            textSize = 20.sp,
            height = 60.dp,
            shape = RoundedCornerShape(30.dp)
        )
    }
}
