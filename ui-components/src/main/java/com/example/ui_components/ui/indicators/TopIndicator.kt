package com.example.ui_components.ui.indicators

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopIndicator(
    modifier: Modifier = Modifier,
    title: String,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
    leadingTextStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Medium),
    trailingTextStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Medium),
    contentColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = .8f),
    containerColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = .1f),
    iconSizes: Dp = 50.dp,
    leadingText: String = "",
    trailingText: String = "",
    leadingIcon: ImageVector = Icons.AutoMirrored.Default.ArrowBack,
    trailingIcon: ImageVector = Icons.Default.Home,
    onLeadingIconClicked: () -> Unit,
    onTrailingIconClicked: () -> Unit,
    titleLength: Int = 0
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(CircleShape)
            .background(containerColor)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(2) { index ->
            val btn = when (index) {
                0 -> listOf(leadingIcon, leadingText, leadingTextStyle)
                1 -> listOf(trailingIcon, trailingText, trailingTextStyle)
                else -> emptyList()
            }
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(CircleShape)
                    .background(containerColor)
                    .clickable {
                        when (index) {
                            0 -> onLeadingIconClicked()
                            1 -> onTrailingIconClicked()
                        }
                    }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(iconSizes)
                        .clip(CircleShape),
                    imageVector = btn[0] as ImageVector,
                    contentDescription = null,
                    tint = contentColor
                )
                if ("${btn[1]}".isNotEmpty()) {
                    Text(
                        text = "${btn[1]}",
                        style = btn[2] as TextStyle
                    )
                }
            }

            if (index == 0) {
                Text(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.small)
                        .padding(10.dp),
                    text = if (titleLength != 0) title.take(titleLength) else title,
                    color = contentColor,
                    style = titleStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}