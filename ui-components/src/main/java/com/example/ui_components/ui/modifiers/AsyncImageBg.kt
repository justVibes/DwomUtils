package com.example.ui_components.ui.modifiers

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ui_components.ui.core.CustomColor

@Composable
fun Modifier.asyncImageBg(
    loadedBgColor: Color = CustomColor.photoFadedGray(),
    isLoading: Boolean
) = if (isLoading) {
    shimmerEffect()
} else {
    background(loadedBgColor)
}
