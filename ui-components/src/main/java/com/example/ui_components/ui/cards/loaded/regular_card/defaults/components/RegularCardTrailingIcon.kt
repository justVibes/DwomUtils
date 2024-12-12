package com.example.ui_components.ui.cards.loaded.regular_card.defaults.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.takeOrElse

class RegularCardTrailingIcon(
    val shape: RoundedCornerShape,
    val size: Dp
) {
    fun copy(
         shape: RoundedCornerShape = this.shape,
         size: Dp = this.size
    ) = RegularCardTrailingIcon(
        shape = shape,
        size = size.takeOrElse { this.size }
    )
}