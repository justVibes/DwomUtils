package com.example.ui_components.ui.indicators.top_indicator.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape


data class TopIndicatorContainerDefaults(
    val color: @Composable () -> Color = { TopIndicatorColors.defaultContainerColor },
    val shape: Shape = CircleShape
)