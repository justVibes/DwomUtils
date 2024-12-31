package com.example.ui_components.ui.cards.loaded.regular_card.components.config

import androidx.compose.material3.SwipeToDismissBoxValue
import com.example.ui_components.ui.cards.loaded.regular_card.components.SwipeSide

data class SwipeableRegularCardConfig<T: Any?>(
    val swipeSides: Pair<SwipeSide, SwipeSide>,
    val onSwipe: (SwipeToDismissBoxValue, T) -> Boolean
)