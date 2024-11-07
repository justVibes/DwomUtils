package com.example.ui_components.ui.cards.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

data class HeaderContent(
    val text: String,
    val separator: Char = ' ',
    val style: @Composable () -> TextStyle = {
        MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    },
    val separateText: Boolean = false,
    val maxLines: Int = 1,
    val overflow: TextOverflow = TextOverflow.Ellipsis
)






