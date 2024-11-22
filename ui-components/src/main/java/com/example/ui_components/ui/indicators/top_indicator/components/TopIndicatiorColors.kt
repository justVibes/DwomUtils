package com.example.ui_components.ui.indicators.top_indicator.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

object TopIndicatorColors {
    val defaultIconColor @Composable get() = MaterialTheme.colorScheme.surface.copy(alpha = .8f)
    val defaultContainerColor @Composable get() = MaterialTheme.colorScheme.surface.copy(alpha = .1f)
}