package com.example.ui_components.ui.cards.loaded.regular_card.config

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class RegularCardTrailingConfig(
    val icon: ImageVector? = null,
    val content: (@Composable () -> Unit)? = null,
    val punchSupportText: String = "",
)