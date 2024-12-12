package com.example.ui_components.ui.cards.loaded.indicator_card.defaults.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.takeOrElse

class IndicatorCardImage(
    val size: Dp,
    @DrawableRes val placeholder: Int,
    val shape: RoundedCornerShape,
    val borderColor: Color,
    val borderThickness: Dp,
    val backgroundColor: Color
) {
    fun copy(
        size: Dp = this.size,
        placeholder: Int = this.placeholder,
        shape: RoundedCornerShape = this.shape,
        borderColor: Color = this.borderColor,
        backgroundColor: Color = this.backgroundColor,
        borderThickness: Dp = this.borderThickness
    ) = IndicatorCardImage(
        size = size.takeOrElse { this.size },
        placeholder = placeholder,
        shape = shape,
        borderColor = borderColor.takeOrElse { this.borderColor },
        backgroundColor = backgroundColor.takeOrElse { this.backgroundColor },
        borderThickness = borderThickness.takeOrElse { this.borderThickness }
    )
}