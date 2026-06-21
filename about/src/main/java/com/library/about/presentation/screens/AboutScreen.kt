package com.library.about.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Whatsapp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.about.presentation.component.AboutDescriptionCard
import com.library.about.presentation.component.AboutInfoCard
import com.library.core.R
import com.library.core.ui.theme.Tajawal

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(32.dp),
                        ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        spotColor = MaterialTheme.colorScheme.primary
                    )
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(110.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "مكتبة الرواق",
                fontSize = 32.sp,
                fontFamily = Tajawal,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )

            Text(
                text = "حيث تلتقي الكلمات بالورق",
                fontSize = 16.sp,
                fontFamily = Tajawal,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AboutDescriptionCard(
                    title = "عن المكتبة",
                    description = "مكتبة الرواق هي وجهتك الأولى لاقتناء أدوات القرطاسية الفاخرة والكتب المختارة بعناية. نقدم منتجات أصلية بجودة عالية تناسب الطلاب والكتاب والفنانين."
                )

                AboutInfoCard(
                    label = "العنوان",
                    value = "شارع الأمير محمد، حي الياسمين، الرياض",
                    icon = Icons.Outlined.LocationOn
                )

                AboutInfoCard(
                    label = "رقم الهاتف",
                    value = "5678 234 11 966+",
                    icon = Icons.Outlined.Phone,
                    actionButtonText = "اتصال",
                    onActionClick = { /* Handle Call */ }
                )

                AboutInfoCard(
                    label = "واتساب",
                    value = "4567 123 55 966+",
                    icon = Icons.Outlined.Whatsapp,
                    actionButtonText = "محادثة",
                    onActionClick = { /* Handle Chat */ },
                )

                AboutInfoCard(
                    label = "أوقات العمل",
                    value = "السبت - الخميس: 9 ص - 11 م | الجمعة: 4 م - 11 م",
                    icon = Icons.Outlined.AccessTime
                )
            }
        }
        item {
            Spacer(Modifier.height(128.dp))
        }
    }
}
