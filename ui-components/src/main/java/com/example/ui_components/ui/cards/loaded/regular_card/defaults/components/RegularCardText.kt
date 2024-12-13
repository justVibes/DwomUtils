package com.example.ui_components.ui.cards.loaded.regular_card.defaults.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

class RegularCardText(
    val headerStyle: TextStyle,
    val subHeaderStyle: TextStyle,
    val separateHeader: Boolean,
    val separateSubHeader: Boolean,
    val headerMaxLines: Int,
    val subHeaderMaxLines: Int,
    val headerOverflow: TextOverflow,
    val subHeaderOverflow: TextOverflow,
) {
    fun copy(
        headerStyle: TextStyle = this.headerStyle,
        subHeaderStyle: TextStyle = this.subHeaderStyle,
        separateHeader: Boolean = this.separateHeader,
        separateSubHeader: Boolean = this.separateSubHeader,
        headerMaxLines: Int = this.headerMaxLines,
        subHeaderMaxLines: Int = this.subHeaderMaxLines,
        headerOverflow: TextOverflow = this.headerOverflow,
        subHeaderOverflow: TextOverflow = this.subHeaderOverflow,
    ) = RegularCardText(
        headerStyle = headerStyle,
        separateHeader = separateHeader,
        headerMaxLines = headerMaxLines,
        headerOverflow = headerOverflow,
        subHeaderStyle = subHeaderStyle,
        separateSubHeader = separateSubHeader,
        subHeaderMaxLines = subHeaderMaxLines,
        subHeaderOverflow = subHeaderOverflow
    )

    @Composable
    internal fun style(isHeader: Boolean) = if (isHeader) headerStyle else subHeaderStyle

    @Composable
    internal fun separateText(isHeader: Boolean) =
        if (isHeader) separateHeader else separateSubHeader

    @Composable
    internal fun maxLines(isHeader: Boolean) = if (isHeader) headerMaxLines else subHeaderMaxLines

    @Composable
    internal fun overflow(isHeader: Boolean) = if (isHeader) headerOverflow else subHeaderOverflow
}