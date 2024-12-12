package com.example.ui_components.ui.cards.loaded.indicator_card.defaults

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.ui_components.R
import com.example.ui_components.ui.cards.loaded.indicator_card.defaults.components.IndicatorCardColors
import com.example.ui_components.ui.cards.loaded.indicator_card.defaults.components.IndicatorCardImage
import com.example.ui_components.ui.cards.loaded.indicator_card.defaults.components.IndicatorCardIndicator
import kotlin.random.Random

object IndicatorCardDefaults {
    @Composable
    fun colors(
        containerColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        indicatorColor: Color = Color(Random.nextInt()),
        indicatorCutoutColor: Color = MaterialTheme.colorScheme.surfaceVariant,
        trailingIconColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    ) = IndicatorCardColors(
        containerColor = containerColor,
        contentColor = contentColor,
        indicatorColor = indicatorColor,
        indicatorCutoutColor = indicatorCutoutColor,
        trailingIconColor = trailingIconColor,
    )

    @Composable
    fun image(
        size: Dp = 40.dp,
        placeholder: Int = R.drawable.ic_person,
        shape: RoundedCornerShape = CircleShape,
        borderColor: Color = MaterialTheme.colorScheme.onSurface,
        borderThickness: Dp = 1.dp,
        backgroundColor: Color = MaterialTheme.colorScheme.onSurface.copy(.25f)
    ) = IndicatorCardImage(
        size = size,
        placeholder = placeholder,
        shape = shape,
        borderColor = borderColor,
        borderThickness = borderThickness,
        backgroundColor = backgroundColor
    )

    fun indicator(
        height: Dp = 60.dp,
        thickness: Dp = 5.dp,
        cutoutSize: Dp = 10.dp,
        cutoutOffset: DpOffset = DpOffset(x = (-3).dp, y = Dp.Unspecified)
    ) = IndicatorCardIndicator(
        height = height,
        thickness = thickness,
        cutoutSize = cutoutSize,
        cutoutOffset = cutoutOffset
    )
}