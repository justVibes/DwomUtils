package com.example.ui_components.ui.indicators.top_indicator.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class TopIndicatorIconDefaults(
    val icon: ImageVector,
    val size: Dp = 50.dp,
    val shape: Shape = CircleShape,
    val bgColor: @Composable () -> Color = { TopIndicatorColors.defaultContainerColor },
    val iconColor: @Composable () -> Color = { TopIndicatorColors.defaultIconColor }
)