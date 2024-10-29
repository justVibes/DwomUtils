package com.example.ui_components.core

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import kotlin.math.max

object TextStyling {
    @Composable
    fun ColorDifference(
        text: String,
        color: Color,
        style: TextStyle,
        separator: Char
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = color)) {
                    append(text.takeWhile { it != separator })
                }
                withStyle(SpanStyle(color = color.copy(alpha = .7f))) {
                    append(text.takeLastWhile { it != separator })
                }
            },
            style = style,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}