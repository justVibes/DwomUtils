package com.example.ui_components.ui.cards.loaded.regular_card

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.ui_components.ui.cards.loaded.regular_card.config.LockedRegularCardConfig
import com.example.ui_components.ui.cards.loaded.regular_card.config.RegularCardLeadingConfig
import com.example.ui_components.ui.cards.loaded.regular_card.config.RegularCardTextConfig
import com.example.ui_components.ui.cards.loaded.regular_card.config.RegularCardTrailingConfig
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.RegularCardDefaults
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.components.RegularCardColors
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.components.RegularCardLeadingImage
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.components.RegularCardPunch
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.components.RegularCardText
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.components.RegularCardTrailingIcon
import com.example.ui_components.ui.core.core_logic.TextStyling

@Composable
fun RegularCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isPunchVisible: Boolean = false,
    lockedConfig: LockedRegularCardConfig? = null,
    leadingContentConfig: RegularCardLeadingConfig,
    header: RegularCardTextConfig,
    subHeader: RegularCardTextConfig,
    trailingContentConfig: RegularCardTrailingConfig? = null,
    shape: CornerBasedShape? = MaterialTheme.shapes.small,
    shadowElevation: Dp = Dp.Unspecified,
    punchDefaults: RegularCardPunch = RegularCardDefaults.trailingPunch(),
    leadingImageDefaults: RegularCardLeadingImage = RegularCardDefaults.leadingImage(),
    trailingIconDefaults: RegularCardTrailingIcon = RegularCardDefaults.trailingIcon(),
    textDefaults: RegularCardText = RegularCardDefaults.text(),
    colors: RegularCardColors = RegularCardDefaults.colors(),
    onClick: () -> Unit
) {
    val ctx = LocalContext.current
    Box {
        ListItem(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape ?: RoundedCornerShape(0))
                .clickable {
                    if (lockedConfig == null) onClick()
                    else {
                        Toast
                            .makeText(ctx, lockedConfig.displayMessage, Toast.LENGTH_LONG)
                            .show()
                    }
                },
            colors = ListItemDefaults.colors(
                containerColor = colors.containerColor(isSelected),
                headlineColor = colors.contentColor(isSelected),
                supportingColor = colors.contentColor(isSelected)
            ),
            leadingContent = {
                val imgModifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)

                Box(
                    modifier = Modifier
                        .size(leadingImageDefaults.photoSize)
                        .clip(leadingImageDefaults.shape)
                        .background(colors.leadingImageBackgroundColor)
                        .border(
                            width = leadingImageDefaults.borderThickness,
                            color = colors.leadingImageBorderColor,
                            shape = leadingImageDefaults.shape
                        )
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        leadingContentConfig.useLttrsForPhoto != null -> {
                            val style = leadingContentConfig.initialStyle
                                ?: MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            Text(
                                text = leadingContentConfig.useLttrsForPhoto,
                                style = style
                            )
                        }

                        leadingContentConfig.resPhoto != null -> {
                            Image(
                                modifier = imgModifier,
                                painter = painterResource(leadingContentConfig.resPhoto),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }

                        else -> {
                            AsyncImage(
                                modifier = imgModifier,
                                model = leadingContentConfig.photoUrl.toUri(),
                                contentDescription = null,
                                placeholder = leadingImageDefaults.placeholder,
                                error = leadingImageDefaults.placeholder,
                                contentScale = ContentScale.Crop
                            )
                            if (leadingImageDefaults.isUpdateBubbleVisible) {
                                Box(
                                    modifier = Modifier
                                        .align(leadingImageDefaults.updateBubbleAlignment)
                                        .size((leadingImageDefaults.photoSize.value * .25).dp)
                                        .clip(CircleShape)
                                        .background(colors.updateBubbleColor)
                                )
                            }
                        }
                    }
                }
            },
            headlineContent = {
                GetTextContent(
                    textConfig = header,
                    isHeader = true,
                    defaults = textDefaults,
                    colors = colors,
                    isSelected = isSelected
                )
            },
            supportingContent = {
                GetTextContent(
                    textConfig = subHeader,
                    isHeader = false,
                    defaults = textDefaults,
                    colors = colors,
                    isSelected = isSelected
                )
            },
            trailingContent = {
                when {
                    trailingContentConfig?.content != null -> {
                        trailingContentConfig.content.invoke()
                    }

                    trailingContentConfig?.icon != null -> {
                        Icon(
                            modifier = Modifier
                                .size(trailingIconDefaults.size)
                                .clip(trailingIconDefaults.shape)
                                .background(colors.trailingIconBackgroundColor)
                                .clickable { trailingContentConfig.onTrailingIconClicked() }
                                .padding(10.dp),
                            imageVector = trailingContentConfig.icon,
                            contentDescription = null,
                            tint = colors.trailingIconColor
                        )
                    }

                    isPunchVisible -> {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(((leadingImageDefaults.photoSize.value.toInt() / 2) * .7).dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(punchDefaults.copy(size = (leadingImageDefaults.photoSize.value * .25).dp).size)
                                    .clip(CircleShape)
                                    .background(colors.punchColor)
                            )
                            if (trailingContentConfig?.punchSupportText != null) {
                                Text(
                                    text = trailingContentConfig.punchSupportText,
                                    style = punchDefaults.textStyle
                                )
                            }
                        }
                    }
                }
            },
            shadowElevation = shadowElevation
        )

        if (lockedConfig != null) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(shape ?: RoundedCornerShape(0))
                    .background(colors.lockBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = colors.lockIconColor
                )
            }
        }
    }
}

@Composable
private fun GetTextContent(
    textConfig: RegularCardTextConfig,
    isHeader: Boolean,
    defaults: RegularCardText,
    colors: RegularCardColors,
    isSelected: Boolean,
) {
    val color =
        if (isHeader) colors.containerColor(isSelected) else colors.containerColor(isSelected)
            .copy(.5f)
    when {
        textConfig.annotatedText != null -> {
            Text(
                text = textConfig.annotatedText,
                style = defaults.style(isHeader),
                color = color
            )
        }

        defaults.separateText(isHeader) -> {
            TextStyling.ColorDifference(
                text = textConfig.text,
                color = color,
                style = defaults.style(isHeader),
                separator = textConfig.separator!!,
            )
        }

        else -> {
            Text(
                text = textConfig.text,
                style = defaults.style(isHeader),
                maxLines = defaults.maxLines(isHeader),
                overflow = defaults.overflow(isHeader),
                color = color
            )
        }
    }
}


