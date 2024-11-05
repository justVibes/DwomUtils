package com.example.ui_components.ui.cards.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_components.ui.core.CustomColor

data class LeadingImage(
    val isProfileImage: Boolean = false,
    val photoUrl: String = "",
    val photoSize: Dp = 50.dp,
    val backgroundColor: @Composable () -> Color = { CustomColor.photoFadedGray() },
    val borderColor: @Composable () -> Color = { CustomColor.photoFadedGrayBorder() },
)
