package com.example.ui_components.ui.core.core_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CloseBtn(
    modifier: Modifier = Modifier,
    size: Dp = 35.dp,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    containerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = .05f),
    isEnabled: Boolean = true,
    onClick: () -> Unit

) {
    Icon(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(containerColor)
            .clickable(enabled = isEnabled) { onClick() }
            .padding(5.dp),
        imageVector = Icons.Default.Close,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSurface
    )
}