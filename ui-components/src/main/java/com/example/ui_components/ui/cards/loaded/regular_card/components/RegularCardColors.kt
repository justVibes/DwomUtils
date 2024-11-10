package com.example.ui_components.ui.cards.loaded.regular_card.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.ui_components.ui.core.CustomColor

data class RegularCardColors(
    val unfocusedContainerColor: @Composable () -> Color = { CustomColor.cardFadedGray() },
    val unfocusedContentColor: @Composable () -> Color = { MaterialTheme.colorScheme.inverseSurface },
    val focusedContainerColor: @Composable () -> Color = { CustomColor.cardFadedGraySelected() },
    val focusedContentColor: @Composable () -> Color = { MaterialTheme.colorScheme.surface },
)