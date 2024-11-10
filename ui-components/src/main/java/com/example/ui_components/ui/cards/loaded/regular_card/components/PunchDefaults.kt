package com.example.ui_components.ui.cards.loaded.regular_card.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class PunchDefaults(
    val color: @Composable () -> Color = {MaterialTheme.colorScheme.onSurface},
    val supportText: String = "",
    val supportTextStyle: @Composable () -> TextStyle ={MaterialTheme.typography.titleSmall},
    val punchSize: Dp = Dp.Unspecified
)
