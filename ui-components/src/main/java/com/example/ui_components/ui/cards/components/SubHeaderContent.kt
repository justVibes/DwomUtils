package com.example.ui_components.ui.cards.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow

data class SubHeaderContent(
    val text: String,
    val separator: Char = ' ',
    val style: @Composable () -> TextStyle = {
        MaterialTheme.typography.titleSmall.copy(
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    },
    val separateText: Boolean = false,
    val maxLines: Int = 1,
    val overflow: TextOverflow = TextOverflow.Ellipsis
)
