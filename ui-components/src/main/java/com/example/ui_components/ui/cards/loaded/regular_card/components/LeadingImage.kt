package com.example.ui_components.ui.cards.loaded.regular_card.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_components.ui.core.core_logic.CustomColor

data class LeadingImage(
    val isProfileImage: Boolean = false,
    val photoUrl: String = "",
    val photoSize: Dp = 50.dp,
    val backgroundColor: @Composable () -> Color = {
        if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.onSurface.copy(alpha = .1f)
        } else {
            MaterialTheme.colorScheme.onSurface.copy(alpha = .25f)
        }
    },
    val borderColor: @Composable () -> Color = {
        if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.outline
        }
        else {
            CustomColor.photoFadedGray()
        }
    },
)
