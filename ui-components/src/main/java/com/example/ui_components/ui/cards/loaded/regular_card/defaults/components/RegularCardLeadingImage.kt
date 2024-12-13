package com.example.ui_components.ui.cards.loaded.regular_card.defaults.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.takeOrElse

class RegularCardLeadingImage(
    val isProfileImage: Boolean,
    val photoSize: Dp,
    val isUpdateBubbleVisible: Boolean,
    val borderThickness: Dp,
    @DrawableRes val profilePlaceholder: Int,
    @DrawableRes val photoPlaceholder: Int,
    val shape: RoundedCornerShape,
    val updateBubbleAlignment: Alignment,
) {
    fun copy(
        isProfileImage: Boolean = this.isProfileImage,
        photoSize: Dp = this.photoSize,
        isUpdateBubbleVisible: Boolean = this.isUpdateBubbleVisible,
        borderThickness: Dp = this.borderThickness,
        @DrawableRes profilePlaceholder: Int = this.profilePlaceholder,
        @DrawableRes photoPlaceholder: Int = this.photoPlaceholder,
        shape: RoundedCornerShape = this.shape,
        updateBubbleAlignment: Alignment = this.updateBubbleAlignment
    ) = RegularCardLeadingImage(
        isProfileImage = isProfileImage,
        photoSize = photoSize.takeOrElse { this.photoSize },
        isUpdateBubbleVisible = isUpdateBubbleVisible,
        borderThickness = borderThickness.takeOrElse { this.borderThickness },
        profilePlaceholder = profilePlaceholder,
        photoPlaceholder = photoPlaceholder,
        shape = shape,
        updateBubbleAlignment = updateBubbleAlignment
    )

    internal val placeholder: Painter
        @Composable get() = if (isProfileImage) painterResource(profilePlaceholder)
        else painterResource(photoPlaceholder)
}
