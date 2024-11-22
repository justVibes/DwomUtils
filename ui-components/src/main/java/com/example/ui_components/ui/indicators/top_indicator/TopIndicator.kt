package com.example.ui_components.ui.indicators.top_indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ui_components.ui.indicators.top_indicator.components.TopIndicatorContainerDefaults
import com.example.ui_components.ui.indicators.top_indicator.components.TopIndicatorIconDefaults
import com.example.ui_components.ui.indicators.top_indicator.components.TopIndicatorTextDefaults

val defaultIconColor @Composable get() = MaterialTheme.colorScheme.surface.copy(alpha = .8f)
val defaultContainerColor @Composable get() = MaterialTheme.colorScheme.surface.copy(alpha = .1f)

@Composable
fun TopIndicator(
    modifier: Modifier = Modifier,
    mainContent: (@Composable () -> Unit)? = null,
    mainText: TopIndicatorTextDefaults = TopIndicatorTextDefaults(
        title = "",
        style = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
    ),
    leadingContent: (@Composable () -> Unit)? = null,
    leadingIcon: TopIndicatorIconDefaults = TopIndicatorIconDefaults(
        icon = Icons.AutoMirrored.Default.ArrowBack,
        iconColor = defaultIconColor,
        bgColor = defaultContainerColor
    ),
    trailingContent: (@Composable () -> Unit)? = null,
    trailingIcon: TopIndicatorIconDefaults = TopIndicatorIconDefaults(
        icon = Icons.Default.Home,
        iconColor = defaultIconColor,
        bgColor = defaultContainerColor
    ),
    containerDefaults: TopIndicatorContainerDefaults = TopIndicatorContainerDefaults(
        color = defaultContainerColor
    ),
    onLeadingIconClicked: () -> Unit,
    onTrailingIconClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(containerDefaults.shape)
            .background(containerDefaults.color)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (leadingContent) {
            null -> GetIndicatorBtn(icon = leadingIcon) { onLeadingIconClicked() }
            else -> leadingContent.invoke()
        }

        when (mainContent) {
            null -> {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = mainText.formattedTitle,
                    style = mainText.style,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            else -> mainContent.invoke()
        }

        when (trailingContent) {
            null -> GetIndicatorBtn(icon = trailingIcon) { onTrailingIconClicked() }
            else -> trailingContent.invoke()
        }
    }
}

@Composable
private fun GetIndicatorBtn(
    icon: TopIndicatorIconDefaults,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .clip(icon.shape)
            .background(icon.bgColor)
            .clickable { onClick() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(icon.size)
                .clip(icon.shape),
            imageVector = icon.icon,
            contentDescription = null,
            tint = icon.iconColor
        )
    }
}
