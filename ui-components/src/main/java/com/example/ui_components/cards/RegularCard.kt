package com.example.ui_components.cards

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
import com.example.ui_components.core.CustomColor
import com.example.ui_components.R
import com.example.ui_components.core.TextStyling

@Composable
fun RegularCard(
    modifier: Modifier = Modifier,
    photoUrl: String,
    photoSize: Dp = 50.dp,
    headerText: String,
    headerSeparator: Char = ' ',
    subHeaderSeparator: Char = ' ',
    separateHeader: Boolean = false,
    separateSubHeader: Boolean = false,
    headerStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
    subHeaderText: String,
    subHeaderStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(fontStyle = FontStyle.Italic),
    unfocusedContainerColor: Color = CustomColor.cardFadedGray(),
    unfocusedContentColor: Color = MaterialTheme.colorScheme.inverseSurface,
    focusedContainerColor: Color = CustomColor.cardFadedGraySelected(),
    focusedContentColor: Color = MaterialTheme.colorScheme.surface,
    isSelected: Boolean = false,
    isProfileCard: Boolean = false,
    onCardClicked: () -> Unit
) {
    val containerColor by animateColorAsState(if (!isSelected) unfocusedContainerColor else focusedContainerColor)
    val contentColor by animateColorAsState(if (!isSelected) unfocusedContentColor else focusedContentColor)

    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { onCardClicked() },
        colors = ListItemDefaults.colors(
            containerColor = containerColor,
            headlineColor = contentColor,
            supportingColor = contentColor
        ),
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(photoSize)
                    .clip(CircleShape)
                    .background(CustomColor.photoFadedGray())
                    .border(
                        width = 1.dp,
                        color = CustomColor.photoFadedGrayBorder(),
                        shape = CircleShape
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                val placeholderImg = if (!isProfileCard) painterResource(R.drawable.ic_image_placeholder) else painterResource(
                    R.drawable.ic_person
                )
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    model = photoUrl.toUri(),
                    contentDescription = null,
                    placeholder = placeholderImg,
                    error = placeholderImg,
                    contentScale = ContentScale.Crop
                )
            }
        },
        headlineContent = {
            if(separateHeader){
                TextStyling.ColorDifference(
                    text = headerText,
                    color = headerStyle.color,
                    style = headerStyle,
                    separator = headerSeparator
                )
            }else{
                Text(
                    text = headerText,
                    style = headerStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        supportingContent = {
            if(separateSubHeader){
                TextStyling.ColorDifference(
                    text = subHeaderText,
                    color = subHeaderStyle.color,
                    style = subHeaderStyle,
                    separator = subHeaderSeparator
                )
            }else{
                Text(
                    text = subHeaderText,
                    style = subHeaderStyle
                )
            }
        }
    )
}