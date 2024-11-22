package com.example.ui_components.ui.indicators.top_indicator.components

import androidx.compose.ui.text.TextStyle

data class TopIndicatorTextDefaults(
    val title: String,
    val style: TextStyle,
    val visibleTitleLength: Int = 0,
) {
    val formattedTitle = if (visibleTitleLength > 0) title.take(visibleTitleLength) else title
}