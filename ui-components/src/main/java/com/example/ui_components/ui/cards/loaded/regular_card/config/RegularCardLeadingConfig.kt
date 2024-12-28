package com.example.ui_components.ui.cards.loaded.regular_card.config

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.TextStyle

data class RegularCardLeadingConfig(
    val photoUrl: String = "",
    @DrawableRes val resPhoto: Int? = null,
    val useLttrsForPhoto: String? = null,
    val initialStyle: TextStyle? = null
)
