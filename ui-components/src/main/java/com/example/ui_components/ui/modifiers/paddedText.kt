package com.example.ui_components.ui.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.paddedText() = this
    .clip(MaterialTheme.shapes.small)
    .background(MaterialTheme.colorScheme.onSurface.copy(.05f))
    .padding(5.dp)