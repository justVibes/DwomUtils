package com.example.ui_components.ui.core.core_logic

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object WindowSizes {
    fun photoSizeSmall(windowSizeClass: WindowSizeClass): Dp {
        return when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 50.dp
            WindowWidthSizeClass.Medium -> 100.dp
            WindowWidthSizeClass.Expanded -> 130.dp
            else -> 60.dp
        }
    }

    fun photoSizeMedium(windowSizeClass: WindowSizeClass): Dp {
        return when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 50.dp
            WindowWidthSizeClass.Medium -> 100.dp
            WindowWidthSizeClass.Expanded -> 130.dp
            else -> 60.dp
        }
    }
    fun photoSizeLarge(windowSizeClass: WindowSizeClass): Dp {
        return when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 100.dp
            WindowWidthSizeClass.Medium -> 115.dp
            WindowWidthSizeClass.Expanded -> 150.dp
            else -> 60.dp
        }
    }

    fun textSizeSmall(windowSizeClass: WindowSizeClass): TextUnit {
        return when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 18.sp
            WindowWidthSizeClass.Medium -> 21.sp
            WindowWidthSizeClass.Expanded -> 24.sp
            else -> 16.sp
        }
    }

    fun textSizeMedium(windowSizeClass: WindowSizeClass): TextUnit {
        return when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 18.sp
            WindowWidthSizeClass.Medium -> 24.sp
            WindowWidthSizeClass.Expanded -> 28.sp
            else -> 16.sp
        }
    }

    fun textSizeLarge(windowSizeClass: WindowSizeClass): TextUnit {
        return when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 26.sp
            WindowWidthSizeClass.Medium -> 30.sp
            WindowWidthSizeClass.Expanded -> 36.sp
            else -> 16.sp
        }
    }
}