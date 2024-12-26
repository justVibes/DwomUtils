package com.example.ui_components.ui.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.neoContainer(
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    containerPadding: Dp = 10.dp,
    shadowElevation: Dp = 10.dp,
) = this
    .shadow(
        elevation = shadowElevation,
        shape = shape,
        ambientColor = MaterialTheme.colorScheme.onSurface,
        spotColor = MaterialTheme.colorScheme.onSurfaceVariant,
    )
    .fillMaxWidth()
    .clip(shape)
    .background(MaterialTheme.colorScheme.surfaceVariant)
    .padding(containerPadding)