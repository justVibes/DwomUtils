package com.example.ui_components.ui.indicators

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    amountOfPages: Int,
    currentPage: Int,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        repeat(amountOfPages) {
            Box(
                Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = if (currentPage < it) color else Transparent,
                        shape = CircleShape
                    )
                    .background(if (currentPage == it || currentPage > it) color else Transparent)
            )
            if (it != amountOfPages - 1) {
                Spacer(
                    Modifier
                        .width(10.dp)
                        .height(3.dp)
                        .background(color)
                )
            }
        }
    }
}