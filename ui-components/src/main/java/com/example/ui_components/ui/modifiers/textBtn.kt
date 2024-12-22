package com.example.ui_components.ui.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.textBtn(
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    containerColor: Color = MaterialTheme.colorScheme.onSurface.copy(.15f),
    padding: Dp = 10.dp,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "textBtn"
        properties["shape"] = shape
        properties["containerColor"] = containerColor
        properties["onClick"] = onClick
    },
    factory = {
        Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(containerColor)
            .clickable { onClick() }
            .padding(padding)
    }
)