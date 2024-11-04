package com.example.ui_components.ui.cards

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.ui_components.ui.core.CustomColor
import com.example.ui_components.R
import com.example.ui_components.ui.core.TextStyling

@Composable
fun RegularCard(
    modifier: Modifier = Modifier,
    leadingImage: LeadingImage,
    header: CardTextContent,
    subHeader: CardTextContent,
    trailingIcon: TrailingIcon? = null,
    colors: RegularCardColors = RegularCardColors(),
    shadowElevation: Dp = Dp.Unspecified,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val containerColor by animateColorAsState(
        if (!isSelected) colors.unfocusedContainerColor.invoke() else colors.focusedContainerColor.invoke(),
        label = "containerColor"
    )
    val contentColor by animateColorAsState(
        if (!isSelected) colors.unfocusedContentColor.invoke() else colors.focusedContentColor.invoke(),
        label = "contentColor"
    )

    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { onClick() },
        colors = ListItemDefaults.colors(
            containerColor = containerColor,
            headlineColor = contentColor,
            supportingColor = contentColor
        ),
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(leadingImage.photoSize)
                    .clip(CircleShape)
                    .background(leadingImage.backgroundColor.invoke())
                    .border(
                        width = 1.dp,
                        color = leadingImage.borderColor.invoke(),
                        shape = CircleShape
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                val placeholderImg =
                    if (!leadingImage.isProfileImage) painterResource(R.drawable.ic_image_placeholder)
                    else painterResource(R.drawable.ic_person)
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    model = leadingImage.photoUrl.toUri(),
                    contentDescription = null,
                    placeholder = placeholderImg,
                    error = placeholderImg,
                    contentScale = ContentScale.Crop
                )
            }
        },
        headlineContent = {
            if (header.separateText) {
                TextStyling.ColorDifference(
                    text = header.text,
                    color = header.style.invoke().color,
                    style = header.style.invoke(),
                    separator = header.separator,
                )
            } else {
                Text(
                    text = header.text,
                    style = header.style.invoke(),
                    maxLines = header.maxLines,
                    overflow = header.overflow
                )
            }
        },
        supportingContent = {
            if (subHeader.separateText) {
                TextStyling.ColorDifference(
                    text = subHeader.text,
                    color = subHeader.style.invoke().color,
                    style = subHeader.style.invoke(),
                    separator = subHeader.separator
                )
            } else {
                Text(
                    text = subHeader.text,
                    style = subHeader.style.invoke(),
                    maxLines = subHeader.maxLines,
                    overflow = subHeader.overflow
                )
            }
        },
        trailingContent = {
            if (trailingIcon != null) {
                Icon(
                    modifier = Modifier
                        .size(trailingIcon.size)
                        .clip(CircleShape)
                        .background(trailingIcon.backgroundColor.invoke())
                        .clickable { trailingIcon.onClick() }
                        .padding(10.dp),
                    imageVector = trailingIcon.icon,
                    contentDescription = null,
                    tint = trailingIcon.iconColor.invoke()
                )
            }
        },
        shadowElevation = shadowElevation
    )
}

data class CardTextContent(
    val text: String,
    val isHeader: Boolean = false,
    val separator: Char = ' ',
    val style: @Composable () -> TextStyle = {
        if (isHeader) {
            MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
        } else {
            MaterialTheme.typography.titleSmall.copy(fontStyle = FontStyle.Italic)
        }
    },
    val separateText: Boolean = false,
    val maxLines: Int = 1,
    val overflow: TextOverflow = TextOverflow.Ellipsis
)

data class LeadingImage(
    val isProfileImage: Boolean = false,
    val photoUrl: String = "",
    val photoSize: Dp = 50.dp,
    val backgroundColor: @Composable () -> Color = { CustomColor.photoFadedGray() },
    val borderColor: @Composable () -> Color = { CustomColor.photoFadedGrayBorder() },
)

data class TrailingIcon(
    val icon: ImageVector,
    val size: Dp = 30.dp,
    val onClick: () -> Unit = {},
    val backgroundColor: @Composable () -> Color = { Color.Unspecified },
    val iconColor: @Composable () -> Color = { MaterialTheme.colorScheme.onSurface }
)


data class RegularCardColors(
    val unfocusedContainerColor: @Composable () -> Color = { CustomColor.cardFadedGray() },
    val unfocusedContentColor: @Composable () -> Color = { MaterialTheme.colorScheme.inverseSurface },
    val focusedContainerColor: @Composable () -> Color = { CustomColor.cardFadedGraySelected() },
    val focusedContentColor: @Composable () -> Color = { MaterialTheme.colorScheme.surface },
)
