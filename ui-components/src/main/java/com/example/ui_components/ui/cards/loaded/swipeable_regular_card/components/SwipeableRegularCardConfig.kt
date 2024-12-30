package com.example.ui_components.ui.cards.loaded.swipeable_regular_card.components

import androidx.compose.material3.SwipeToDismissBoxValue

data class SwipeableRegularCardConfig(
    val swipeSides: Pair<SwipeSide, SwipeSide>,
    val onSwipe: (SwipeToDismissBoxValue) -> Boolean
)