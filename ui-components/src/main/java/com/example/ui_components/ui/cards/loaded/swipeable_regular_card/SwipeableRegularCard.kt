package com.example.ui_components.ui.cards.loaded.swipeable_regular_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_components.ui.cards.loaded.regular_card.RegularCard
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
import com.example.ui_components.ui.cards.loaded.swipeable_regular_card.components.SwipeSide
import com.example.ui_components.ui.cards.loaded.swipeable_regular_card.components.SwipeableRegularCardConfig

@Composable
fun SwipeableRegularCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isPunchVisible: Boolean = false,
    swipeableConfig: SwipeableRegularCardConfig,
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
    val dismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = { swipeableConfig.onSwipe(it) },
        positionalThreshold = { it * .25f },
    )

    SwipeToDismissBox(
        state = dismissBoxState,
        backgroundContent = {
            DismissBackground(
                dismissState = dismissBoxState,
                swipeSides = swipeableConfig.swipeSides,
            )
        },
    ) {
        RegularCard(
            modifier = modifier,
            isSelected = isSelected,
            isPunchVisible = isPunchVisible,
            lockedConfig = lockedConfig,
            leadingContentConfig = leadingContentConfig,
            header = header,
            subHeader = subHeader,
            trailingContentConfig = trailingContentConfig,
            shape = shape,
            shadowElevation = shadowElevation,
            punchDefaults = punchDefaults,
            leadingImageDefaults = leadingImageDefaults,
            trailingIconDefaults = trailingIconDefaults,
            textDefaults = textDefaults,
            colors = colors,
            onClick = onClick
        )
    }
}

@Composable
private fun DismissBackground(
    dismissState: SwipeToDismissBoxState,
    swipeSides: Pair<SwipeSide, SwipeSide>
) {
    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> swipeSides.first.color
        SwipeToDismissBoxValue.EndToStart -> swipeSides.second.color
        SwipeToDismissBoxValue.Settled -> Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.small)
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(2) { idx ->
            val side = when (idx) {
                0 -> swipeSides.first
                1 -> swipeSides.second
                else -> swipeSides.first
            }

            Icon(
                imageVector = side.icon,
                contentDescription = null
            )
        }
    }
}