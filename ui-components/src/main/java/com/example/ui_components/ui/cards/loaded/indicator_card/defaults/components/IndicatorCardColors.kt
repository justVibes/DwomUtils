package com.example.ui_components.ui.cards.loaded.indicator_card.defaults.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

class IndicatorCardColors(
    val containerColor: Color,
    val contentColor: Color,
    val indicatorColor: Color,
    val indicatorCutoutColor: Color,
    val trailingIconColor: Color
) {
    fun copy(
        containerColor: Color = this.containerColor,
        contentColor: Color = this.contentColor,
        indicatorColor: Color = this.indicatorColor,
        indicatorCutoutColor: Color = this.indicatorCutoutColor,
        trailingIconColor: Color = this.trailingIconColor
    ) = IndicatorCardColors(
        containerColor = containerColor.takeOrElse { this.containerColor },
        contentColor = contentColor.takeOrElse { this.contentColor },
        indicatorColor = indicatorColor.takeOrElse { this.indicatorColor },
        indicatorCutoutColor = indicatorCutoutColor.takeOrElse { this.indicatorCutoutColor },
        trailingIconColor = trailingIconColor.takeOrElse { this.trailingIconColor }
    )
}