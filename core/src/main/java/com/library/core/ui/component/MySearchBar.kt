package com.library.core.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.library.core.ui.theme.Tajawal


@Composable
fun MySearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    autoFocus: Boolean = false,
    readOnly: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val colors = MaterialTheme.colorScheme

    LaunchedEffect(autoFocus) {
        if (autoFocus) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .focusRequester(focusRequester)
            .border(
                2.dp,
                colors.onPrimary.copy(alpha = 0.6f),
                RoundedCornerShape(24.dp)
            )
            .then(
                if (onClick != null) Modifier.clickable {
                    onClick()
                } else Modifier
            ),
        readOnly = readOnly,
        shape = RoundedCornerShape(24.dp),
        placeholder = {
            Text(
                "ابحث عن منتج أو كتاب أو قسم...",
                fontFamily = Tajawal,
                fontSize = 14.sp,
                color = colors.secondary
            )
        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null,tint = colors.secondary)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colors.surface,
            unfocusedContainerColor = colors.surface,
            cursorColor = colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor =  colors.secondary,
            unfocusedTextColor =  colors.secondary,
        ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = Tajawal,
            fontSize = 14.sp,
            color = colors.secondary
        )
    )
}