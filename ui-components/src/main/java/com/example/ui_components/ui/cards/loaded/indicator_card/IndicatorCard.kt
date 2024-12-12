package com.example.ui_components.ui.cards.loaded.indicator_card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ui_components.ui.cards.loaded.indicator_card.defaults.IndicatorCardDefaults
import com.example.ui_components.ui.cards.loaded.indicator_card.defaults.components.IndicatorCardColors
import com.example.ui_components.ui.cards.loaded.indicator_card.defaults.components.IndicatorCardImage
import com.example.ui_components.ui.cards.loaded.indicator_card.defaults.components.IndicatorCardIndicator

@Composable
fun IndicatorCard(
    headerText: String,
    headerStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
    leadingPhoto: String,
    trailingIcon: ImageVector = Icons.Default.Info,
    colors: IndicatorCardColors = IndicatorCardDefaults.colors(),
    image: IndicatorCardImage = IndicatorCardDefaults.image(),
    indicator: IndicatorCardIndicator = IndicatorCardDefaults.indicator(),
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.containerColor)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Indicator(indicator, colors)
            Content(image, leadingPhoto, headerText, headerStyle)
        }

        Icon(
            modifier = Modifier.padding(10.dp),
            imageVector = trailingIcon,
            contentDescription = null,
            tint = colors.trailingIconColor
        )
    }
}

@Composable
private fun Content(
    image: IndicatorCardImage,
    leadingPhoto: String,
    headerText: String,
    headerStyle: TextStyle
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(
            modifier = Modifier
                .size(image.size)
                .clip(image.shape)
                .border(
                    width = image.borderThickness,
                    color = image.borderColor,
                    shape = image.shape
                )
                .padding(3.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(image.shape)
                    .background(image.backgroundColor),
                model = leadingPhoto,
                contentDescription = null,
                placeholder = painterResource(image.placeholder),
                error = painterResource(image.placeholder),
            )
        }
        Text(
            text = headerText,
            style = headerStyle
        )
    }
}

@Composable
private fun Indicator(
    indicator: IndicatorCardIndicator,
    colors: IndicatorCardColors
) {
    Box(
        modifier = Modifier
            .height(indicator.height)
            .width(indicator.thickness)
            .background(colors.indicatorColor),
        contentAlignment = Alignment.Center
    ) {
        Spacer(
            Modifier
                .offset(x = indicator.cutoutOffset.x, y = indicator.cutoutOffset.y)
                .requiredSize(indicator.cutoutSize)
                .clip(CircleShape)
                .background(colors.indicatorCutoutColor)
        )
    }
}