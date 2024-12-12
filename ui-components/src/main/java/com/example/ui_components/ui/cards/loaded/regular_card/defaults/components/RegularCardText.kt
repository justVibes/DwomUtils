package com.example.ui_components.ui.cards.loaded.regular_card.defaults.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

class RegularCardText(
    val headerStyle: TextStyle,
    val subHeaderStyle: TextStyle,
    val separateText: Boolean,
    val maxLines: Int,
    val overflow: TextOverflow
) {
    fun copy(
        headerStyle: TextStyle = this.headerStyle,
        subHeaderStyle: TextStyle = this.subHeaderStyle,
        separateText: Boolean = this.separateText,
        maxLines: Int = this.maxLines,
        overflow: TextOverflow = this.overflow
    ) = RegularCardText(
        headerStyle = headerStyle,
        separateText = separateText,
        maxLines = maxLines,
        overflow = overflow,
        subHeaderStyle = subHeaderStyle
    )

    @Composable
    internal fun style(isHeader: Boolean) = if (isHeader) headerStyle else subHeaderStyle
}