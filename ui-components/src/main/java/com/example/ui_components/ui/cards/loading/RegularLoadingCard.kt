package com.example.ui_components.ui.cards.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ui_components.ui.cards.loaded.regular_card.components.LeadingImage
import com.example.ui_components.ui.core.core_logic.CustomColor
import com.example.ui_components.ui.modifiers.shimmerEffect

@Composable
fun RegularLoadingCard(
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small),
        colors = ListItemDefaults.colors(
            containerColor = CustomColor.cardFadedGray(),
        ),
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(LeadingImage().photoSize)
                    .clip(CircleShape)
                    .shimmerEffect(),
            )
        },
        headlineContent = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .shimmerEffect(),
                text = "",
                style = MaterialTheme.typography.titleMedium,
            )
        },
        supportingContent = {
            Column {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .clip(MaterialTheme.shapes.small)
                        .shimmerEffect(),
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    )
}