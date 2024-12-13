package com.example.ui_components.ui.cards.loaded.regular_card.defaults

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_components.R
import com.example.ui_components.theme.Teal
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.components.RegularCardColors
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.components.RegularCardLeadingImage
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.components.RegularCardPunch
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.components.RegularCardText
import com.example.ui_components.ui.cards.loaded.regular_card.defaults.components.RegularCardTrailingIcon
import com.example.ui_components.ui.core.core_logic.CustomColor

object RegularCardDefaults {
    @Composable
    fun colors(
        focusedContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(.7f),
        unfocusedContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(.35f),
        focusedContentColor: Color = MaterialTheme.colorScheme.surface,
        unfocusedContentColor: Color = MaterialTheme.colorScheme.onSurface,
        leadingImageBackgroundColor: Color = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.onSurface.copy(alpha = .1f)
        } else {
            MaterialTheme.colorScheme.onSurface.copy(alpha = .25f)
        },
        leadingImageBorderColor: Color = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.outline
        } else {
            CustomColor.photoFadedGray()
        },
        trailingIconColor: Color = MaterialTheme.colorScheme.onSurface,
        trailingIconBackgroundColor: Color = Color.Unspecified,
        punchColor: Color = MaterialTheme.colorScheme.onSurface,
        updateBubbleColor: Color = Teal,
    ) = RegularCardColors(
        focusedContainerColor = focusedContainerColor,
        unfocusedContainerColor = unfocusedContainerColor,
        focusedContentColor = focusedContentColor,
        unfocusedContentColor = unfocusedContentColor,
        leadingImageBackgroundColor = leadingImageBackgroundColor,
        leadingImageBorderColor = leadingImageBorderColor,
        trailingIconColor = trailingIconColor,
        trailingIconBackgroundColor = trailingIconBackgroundColor,
        punchColor = punchColor,
        updateBubbleColor = updateBubbleColor,
    )

    fun leadingImage(
        isProfileImage: Boolean = true,
        photoSize: Dp = 50.dp,
        isUpdateBubbleVisible: Boolean = false,
        borderThickness: Dp = 2.dp,
        @DrawableRes profilePlaceholder: Int = R.drawable.ic_person,
        @DrawableRes photoPlaceholder: Int = R.drawable.ic_photo_placeholder,
        shape: RoundedCornerShape = CircleShape,
        updateBubbleAlignment: Alignment = Alignment.TopEnd,
    ) = RegularCardLeadingImage(
        isProfileImage = isProfileImage,
        photoSize = photoSize,
        isUpdateBubbleVisible = isUpdateBubbleVisible,
        borderThickness = borderThickness,
        profilePlaceholder = profilePlaceholder,
        photoPlaceholder = photoPlaceholder,
        shape = shape,
        updateBubbleAlignment = updateBubbleAlignment
    )

    @Composable
    fun text(
        headerStyle: TextStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
        subHeaderStyle: TextStyle = MaterialTheme.typography.bodyMedium,
        separateHeader: Boolean = false,
        separateSubHeader: Boolean = false,
        headerMaxLines: Int = 1,
        subHeaderMaxLines: Int = 1,
        headerOverflow: TextOverflow = TextOverflow.Ellipsis,
        subHeaderOverflow: TextOverflow = TextOverflow.Ellipsis
    ) = RegularCardText(
        headerStyle = headerStyle,
        subHeaderStyle = subHeaderStyle,
        separateHeader = separateHeader,
        headerMaxLines = headerMaxLines,
        headerOverflow = headerOverflow,
        separateSubHeader = separateSubHeader,
        subHeaderMaxLines = subHeaderMaxLines,
        subHeaderOverflow = subHeaderOverflow
    )

    fun trailingIcon(
        shape: RoundedCornerShape = CircleShape,
        size: Dp = 30.dp
    ) = RegularCardTrailingIcon(
        shape = shape,
        size = size
    )

    @Composable
    fun trailingPunch(
        textStyle: TextStyle = MaterialTheme.typography.titleSmall,
        size: Dp = 15.dp,
    ) = RegularCardPunch(
        textStyle = textStyle,
        size = size
    )
}