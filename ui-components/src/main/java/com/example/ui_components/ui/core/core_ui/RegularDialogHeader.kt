package com.example.ui_components.ui.core.core_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RegularDialogHeader(
    title: String,
    punchColor: Color,
    isBackVisible: Boolean = false,
    isCloseEnabled: Boolean = true,
    windowSizeClass: WindowSizeClass,
    textAlign: Alignment.Horizontal = Alignment.Start,
    onCloseClicked: () -> Unit,
    onBackClicked: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = textAlign
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(2) { idx ->
                val btn = when (idx) {
                    0 -> Icons.AutoMirrored.Default.ArrowBack to onBackClicked
                    1 -> Icons.Default.Close to onCloseClicked
                    else -> Icons.Default.Delete to {}
                }
                if (!isBackVisible && idx == 0) {
                    Box(
                        modifier = Modifier
                            .size(15.dp)
                            .clip(CircleShape)
                            .background(punchColor)
                    )
                    return@repeat
                }
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = .05f))
                        .clickable(enabled = isCloseEnabled) { btn.second() }
                        .padding(5.dp),
                    imageVector = btn.first,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Text(
            text = title,
            style = MaterialTheme.typography.let {
                when (windowSizeClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> it.titleLarge
                    WindowWidthSizeClass.Medium -> it.headlineMedium
                    WindowWidthSizeClass.Expanded -> it.headlineLarge
                    else -> it.titleSmall
                }
            },
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}