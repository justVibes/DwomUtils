package com.example.ui_components.ui.cards.loaded.regular_card.defaults.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

class RegularCardText(
    val isHeader: Boolean,
    val style: TextStyle,
    val separateText: Boolean,
    val maxLines: Int,
    val overflow: TextOverflow
) {
    fun copy(
        isHeader: Boolean = this.isHeader,
        style: TextStyle = this.style,
        separateText: Boolean = this.separateText,
        maxLines: Int = this.maxLines,
        overflow: TextOverflow = this.overflow
    ) = RegularCardText(
        isHeader = isHeader,
        style = style,
        separateText = separateText,
        maxLines = maxLines,
        overflow = overflow
    )

    @Composable
    internal fun style(isHeader: Boolean) = if (isHeader) {
        MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.ExtraBold
        )
    } else {
        MaterialTheme.typography.bodyMedium
    }
}