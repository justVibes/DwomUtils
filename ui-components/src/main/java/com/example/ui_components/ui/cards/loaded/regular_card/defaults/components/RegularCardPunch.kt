package com.example.ui_components.ui.cards.loaded.regular_card.defaults.components

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.takeOrElse

class RegularCardPunch(
    val textStyle: TextStyle,
    val size: Dp,
) {
    fun copy(
        textStyle: TextStyle = this.textStyle,
        size: Dp = this.size,
    ) = RegularCardPunch(
        textStyle = textStyle,
        size = size.takeOrElse { this.size },
    )
}
