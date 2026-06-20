package com.library.core.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.core.ui.theme.Tajawal

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Unspecified,
    textColor: Color = Color.White,
    textSize: TextUnit = 18.sp,
    height: Dp = 56.dp,
    horizontalPadding: Dp = 0.dp,
    shape: RoundedCornerShape = RoundedCornerShape(24.dp)
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
            .height(height),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (backgroundColor == Color.Unspecified) MaterialTheme.colorScheme.primary else backgroundColor,
            contentColor = textColor
        ),
        shape = shape,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            fontSize = textSize,
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontFamily = Tajawal
        )
    }
}
