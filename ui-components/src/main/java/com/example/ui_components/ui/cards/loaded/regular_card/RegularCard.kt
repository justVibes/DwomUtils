package com.example.ui_components.ui.cards.loaded.regular_card

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.ui_components.R
import com.example.ui_components.theme.Teal
import com.example.ui_components.ui.cards.loaded.regular_card.components.CardTextContent
import com.example.ui_components.ui.cards.loaded.regular_card.components.LeadingImage
import com.example.ui_components.ui.cards.loaded.regular_card.components.PunchDefaults
import com.example.ui_components.ui.cards.loaded.regular_card.components.RegularCardColors
import com.example.ui_components.ui.cards.loaded.regular_card.components.TrailingIcon
import com.example.ui_components.ui.core.core_logic.TextStyling

@Composable
fun RegularCard(
    modifier: Modifier = Modifier,
    leadingImage: LeadingImage,
    header: CardTextContent,
    subHeader: CardTextContent,
    trailingIcon: TrailingIcon? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    colors: RegularCardColors = RegularCardColors(),
    shape: CornerBasedShape? = MaterialTheme.shapes.small,
    shadowElevation: Dp = Dp.Unspecified,
    isSelected: Boolean = false,
    punchDefaults: PunchDefaults? = null,
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
            .clip(shape ?: RoundedCornerShape(0))
            .clickable { onClick() },
        colors = ListItemDefaults.colors(
            containerColor = containerColor,
            headlineColor = contentColor,
            supportingColor = contentColor
        ),
        leadingContent = {
            @Composable
            fun Modifier.bgModifier() = composed {
                fillMaxSize()
                clip(CircleShape)
                background(leadingImage.backgroundColor.invoke())
                border(
                    width = 2.dp,
                    color = leadingImage.borderColor.invoke(),
                    shape = CircleShape
                )
                padding(5.dp)
            }


            Box(
                modifier = Modifier.size(leadingImage.photoSize),
                contentAlignment = Alignment.Center
            ) {
                if (leadingImage.resPhoto != 0) {
                    Image(
                        modifier = Modifier.bgModifier(),
                        painter = painterResource(leadingImage.resPhoto),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                } else {
                    val placeholderImg =
                        if (!leadingImage.isProfileImage) painterResource(R.drawable.ic_image_placeholder)
                        else painterResource(R.drawable.ic_person)
                    AsyncImage(
                        modifier = Modifier.bgModifier(),
                        model = leadingImage.photoUrl.toUri(),
                        contentDescription = null,
                        placeholder = placeholderImg,
                        error = placeholderImg,
                        contentScale = ContentScale.Crop
                    )
                    if (leadingImage.isUpdateBubbleVisible) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size((leadingImage.photoSize.value * .25).dp)
                                .clip(CircleShape)
                                .background(Teal)
                        )
                    }
                }
            }
        },
        headlineContent = {
            GetTextContent(header)
        },
        supportingContent = {
            GetTextContent(subHeader)
        },
        trailingContent = {
            when {
                trailingContent != null -> {
                    trailingContent.invoke()
                }

                trailingIcon != null -> {
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

                punchDefaults != null -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(((leadingImage.photoSize.value.toInt() / 2) * .7).dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(if (punchDefaults.punchSize != Dp.Unspecified) punchDefaults.punchSize else (leadingImage.photoSize.value * .25).dp)
                                .clip(CircleShape)
                                .background(punchDefaults.color.invoke())
                        )
                        Text(
                            text = punchDefaults.supportText,
                            style = punchDefaults.supportTextStyle.invoke()
                        )
                    }
                }
            }
        },
        shadowElevation = shadowElevation
    )
}

@Composable
private fun GetTextContent(header: CardTextContent) {
    when {
        header.annotatedText != null -> {
            Text(
                text = header.annotatedText,
                style = header.style.invoke()
            )
        }

        header.separateText -> {
            TextStyling.ColorDifference(
                text = header.text,
                color = header.style.invoke().color,
                style = header.style.invoke(),
                separator = header.separator,
            )
        }

        else -> {
            Text(
                text = header.text,
                style = header.style.invoke(),
                maxLines = header.maxLines,
                overflow = header.overflow
            )
        }
    }
}


