package com.example.ui_components.ui.indicators.top_indicator.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

data class TopIndicatorTextDefaults(
    val title: String,
    val style: @Composable () -> TextStyle = {
        MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
    },
    val visibleTitleLength: Int = 0,
) {
    val formattedTitle = if (visibleTitleLength > 0) title.take(visibleTitleLength) else title
}