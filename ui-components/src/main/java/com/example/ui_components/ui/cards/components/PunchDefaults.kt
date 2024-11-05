package com.example.ui_components.ui.cards.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class PunchDefaults(
    val color: @Composable () -> Color = {MaterialTheme.colorScheme.onSurface},
    val supportText: String = "",
    val supportTextStyle: @Composable () -> TextStyle ={MaterialTheme.typography.titleSmall}
)