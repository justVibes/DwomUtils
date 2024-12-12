package com.example.ui_components.ui.cards.loaded.indicator_card.defaults.components

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.takeOrElse

class IndicatorCardIndicator(
    val height: Dp,
    val thickness: Dp,
    val cutoutSize: Dp,
    val cutoutOffset: DpOffset
) {
    fun copy(
        height: Dp = this.height,
        thickness: Dp = this.thickness,
        cutoutSize: Dp = this.cutoutSize,
        cutoutOffset: DpOffset = this.cutoutOffset
    ) = IndicatorCardIndicator(
        height = height.takeOrElse { this.height },
        thickness = thickness.takeOrElse { this.thickness },
        cutoutSize = cutoutSize.takeOrElse { this.cutoutSize },
        cutoutOffset = cutoutOffset.takeOrElse { this.cutoutOffset }
    )
}