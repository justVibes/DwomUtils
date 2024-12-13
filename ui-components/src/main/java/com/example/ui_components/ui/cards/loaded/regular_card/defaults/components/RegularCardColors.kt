package com.example.ui_components.ui.cards.loaded.regular_card.defaults.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

class RegularCardColors(
    val focusedContainerColor: Color,
    val unfocusedContainerColor: Color,
    val focusedContentColor: Color,
    val unfocusedContentColor: Color,
    val leadingImageBackgroundColor: Color,
    val leadingImageBorderColor: Color,
    val trailingIconColor: Color,
    val trailingIconBackgroundColor: Color,
    val punchColor: Color,
    val updateBubbleColor: Color,
) {
    fun copy(
        focusedContainerColor: Color = this.focusedContainerColor,
        unfocusedContainerColor: Color = this.unfocusedContainerColor,
        focusedContentColor: Color = this.focusedContentColor,
        unfocusedContentColor: Color = this.unfocusedContentColor,
        leadingImageBackgroundColor: Color = this.leadingImageBackgroundColor,
        leadingImageBorderColor: Color = this.leadingImageBorderColor,
        trailingIconColor: Color = this.trailingIconColor,
        trailingIconBackgroundColor: Color = this.trailingIconBackgroundColor,
        punchColor: Color = this.punchColor,
        updateBubbleColor: Color = this.updateBubbleColor,
    ) = RegularCardColors(
        focusedContainerColor = focusedContainerColor.takeOrElse { this.focusedContainerColor },
        unfocusedContainerColor = unfocusedContainerColor.takeOrElse { this.unfocusedContainerColor },
        focusedContentColor = focusedContentColor.takeOrElse { this.focusedContentColor },
        unfocusedContentColor = unfocusedContentColor.takeOrElse { this.unfocusedContentColor },
        leadingImageBackgroundColor = leadingImageBackgroundColor.takeOrElse { this.leadingImageBackgroundColor },
        leadingImageBorderColor = leadingImageBorderColor.takeOrElse { this.leadingImageBorderColor },
        trailingIconColor = trailingIconColor.takeOrElse { this.trailingIconColor },
        trailingIconBackgroundColor = trailingIconBackgroundColor.takeOrElse { this.trailingIconBackgroundColor },
        punchColor = punchColor.takeOrElse { this.punchColor },
        updateBubbleColor = updateBubbleColor,
    )

    internal val containerColor: @Composable (Boolean) -> Color = { isSelected ->
        val color by animateColorAsState(
            if (!isSelected) unfocusedContainerColor else focusedContainerColor,
            label = "regularCardContainerColor"
        )
        color
    }

    internal val contentColor: @Composable (Boolean) -> Color = { isSelected ->
        val color by animateColorAsState(
            if (!isSelected) unfocusedContentColor else focusedContentColor,
            label = "regularCardContentColor"
        )
        color
    }
}

