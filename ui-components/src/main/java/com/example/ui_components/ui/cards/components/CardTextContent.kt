package com.example.ui_components.ui.cards.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

data class CardTextContent(
    val text: String,
    val isHeader: Boolean = false,
    val separator: Char = ' ',
    val style: @Composable () -> TextStyle = {
        if (isHeader) {
            MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
        } else {
            MaterialTheme.typography.titleSmall.copy(fontStyle = FontStyle.Italic)
        }
    },
    val separateText: Boolean = false,
    val maxLines: Int = 1,
    val overflow: TextOverflow = TextOverflow.Ellipsis
)





