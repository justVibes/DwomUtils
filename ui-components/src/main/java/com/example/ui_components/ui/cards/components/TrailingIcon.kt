package com.example.ui_components.ui.cards.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class TrailingIcon(
    val icon: ImageVector,
    val size: Dp = 30.dp,
    val onClick: () -> Unit = {},
    val backgroundColor: @Composable () -> Color = { Color.Unspecified },
    val iconColor: @Composable () -> Color = { MaterialTheme.colorScheme.onSurface }
)