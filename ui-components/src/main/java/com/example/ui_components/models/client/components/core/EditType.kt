package com.example.ui_components.models.client.components.core

import androidx.compose.ui.graphics.Color
import com.example.ui_components.ui.theme.ConfirmBlue
import com.example.ui_components.ui.theme.CreativeGreen

enum class EditType(val color: Color) {
    ADDED(CreativeGreen.copy(.3f)), MODIFIED(ConfirmBlue.copy(alpha = .3f)), NONE(Color.Transparent)
}