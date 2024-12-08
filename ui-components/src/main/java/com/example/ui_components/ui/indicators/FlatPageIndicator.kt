package com.example.ui_components.ui.indicators

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_components.theme.ConfirmBlue

@Composable
fun FlatPageIndicator(
    pages: Int,
    currentPage: Int,
    thickness: Dp = 3.dp,
    gap: Dp = 2.dp,
    focusedColor: Color = ConfirmBlue.copy(.5f),
    unfocusedColor: Color = MaterialTheme.colorScheme.outline,
) {
    Row(
        modifier = Modifier.animateContentSize(),
        horizontalArrangement = Arrangement.spacedBy(gap)
    ) {
        repeat(pages) {
            Spacer(
                Modifier
                    .weight(1f)
                    .height(thickness)
                    .background(if (it == currentPage) focusedColor else unfocusedColor)
            )
        }
    }
}